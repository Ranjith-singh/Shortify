package com.url.shortener.service;

import com.google.api.services.gmail.model.Message;
import com.url.shortener.dtos.EmailRequest;
import com.url.shortener.dtos.LoginRequest;
import com.url.shortener.models.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.url.shortener.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.url.shortener.security.jwt.JwtAuthenticationResponse;
import com.url.shortener.security.jwt.JwtUtils;
import com.url.shortener.service.email.CreateEmail;
import com.url.shortener.service.gmail.CreateMessage;

import lombok.AllArgsConstructor;

import java.io.IOException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public User registerUser(User user){
        System.out.println("user role : User");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest){
        // System.out.println("Login attempt with email: " + loginRequest.getEmail());
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }

    public void sendEmail(EmailRequest emailRequest) throws MessagingException, IOException, Exception{
        // MimeMessage email = CreateEmail.createEmail("ranjithsingh3108@gmail.com",
        //     "ranjithsinghwork4@gmail.com",
        //     "Email Testing",
        //     "message from ranjith");

        // Message message = CreateMessage.CreateMessageWithEmail(email);
        // Message message = SendMessage.sendEmail(emailRequest.getSenderEmail(),
        //     emailRequest.getReceiverEmail(),
        //     emailRequest.getSubject(),
        //     emailRequest.getBody());
    }

    public void changePassword(User user, LoginRequest loginRequest){
        user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        userRepository.save(user);
    }

    public User findByUsername(String name){
        return userRepository.findByUsername(name).orElseThrow(
            ()-> new UsernameNotFoundException("User not found with username: "+name)
        );
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
            ()-> new UsernameNotFoundException("User not found with email: "+email)
        );
    }

    // public Boolean userExists(User user){
    //     return userRepository.existsByUsername(user.getUsername());
    // }

    public Boolean emailExists(User user){
        return userRepository.existsByEmail(user.getEmail());
    }

    public Boolean emailExists(String email){
        return userRepository.existsByEmail(email);
    }
}
