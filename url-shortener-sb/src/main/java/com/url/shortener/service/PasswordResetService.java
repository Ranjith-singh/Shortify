package com.url.shortener.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.shortener.dtos.ChangePasswordDto;
import com.url.shortener.dtos.EmailRequest;
import com.url.shortener.models.PasswordResetToken;
import com.url.shortener.models.User;
import com.url.shortener.repository.PasswordResetTokenRepository;
import com.url.shortener.repository.UserRepository;

@Service
@Transactional
public class PasswordResetService {
    
    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    
    public PasswordResetService(PasswordResetTokenRepository tokenRepository,
                                UserRepository userRepository,
                                EmailService emailService) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
    
    public String requestPasswordReset(EmailRequest emailRequest){
        // System.out.println("email: "+emailRequest.getReceiverEmail());
        User user = userRepository.findByEmail(emailRequest.getReceiverEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Invalidate any existing tokens
        tokenRepository.deleteByUser(user);
        if(tokenRepository.existsByUser(user)){
            new RuntimeException("user not deleted");
        }
        
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, user);
        tokenRepository.save(resetToken);
        
        emailService.sendPasswordResetEmail(emailRequest, token);

        return resetToken.getToken();
    }
    
    public boolean validatePasswordResetToken(String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Invalid token"));
        
        if (resetToken.getExpiryDate().before(new Date())) {
            tokenRepository.delete(resetToken);
            throw new RuntimeException("Token expired");
        }
        
        return true;
    }
    
    public void resetPassword(ChangePasswordDto changePasswordDto) {
        PasswordResetToken resetToken = tokenRepository.findByToken(changePasswordDto.getToken())
            .orElseThrow(() -> new RuntimeException("Invalid token"));
        
        if (resetToken.getExpiryDate().before(new Date())) {
            tokenRepository.delete(resetToken);
            throw new RuntimeException("Token expired");
        }
        
        User user = resetToken.getUser();
        user.setPassword(encodePassword(changePasswordDto.getPassword()));
        userRepository.save(user);
        
        // Delete the used token
        tokenRepository.delete(resetToken);
    }
    
    private String encodePassword(String password) {
        // Use BCryptPasswordEncoder or your preferred encoding method
        return new BCryptPasswordEncoder().encode(password);
    }
}