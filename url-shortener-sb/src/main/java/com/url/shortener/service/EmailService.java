package com.url.shortener.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.url.shortener.dtos.EmailRequest;
import com.url.shortener.service.email.CreateEmail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    
    @Value("${app.base-url}")
    private String appBaseUrl;
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    private final JavaMailSender mailSender;
    
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void sendPasswordResetEmail(EmailRequest emailRequest, String token) {
        try {
            MimeMessage message = CreateEmail.createEmail(emailRequest, token);
            mailSender.send(message);
            
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
