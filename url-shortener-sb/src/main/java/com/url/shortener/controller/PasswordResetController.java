package com.url.shortener.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.http.HttpStatusCodes;
import com.url.shortener.dtos.ChangePasswordDto;
import com.url.shortener.dtos.EmailRequest;
import com.url.shortener.service.PasswordResetService;

@RestController
@RequestMapping("/api/auth")
public class PasswordResetController {
    
    @Autowired
    private PasswordResetService passwordResetService;
    
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody EmailRequest emailRequest) {
        try {
            // System.out.println("email: "+emailRequest);
            String token = passwordResetService.requestPasswordReset(emailRequest);
            return ResponseEntity.ok().body(
                Map.of("message", "Password reset link sent to your email",
                    "token",token)
            );
        } catch (RuntimeException e) {
            // Don't reveal if email exists or not for security
            System.out.println("error :"+e);
            return ResponseEntity.status(HttpStatusCodes.STATUS_CODE_SERVER_ERROR).body("Error sending the link!, check for previous Links in your email");
        }
    }
    
    @GetMapping("/validate-reset-token")
    public ResponseEntity<?> validateResetToken(@RequestParam String token) {
        try {
            passwordResetService.validatePasswordResetToken(token);
            return ResponseEntity.ok().body(
                Map.of("valid", true, "message", "Token is valid")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                Map.of("valid", false, "message", e.getMessage())
            );
        }
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordDto changePasswordDto) {
        try {
            passwordResetService.resetPassword(changePasswordDto);
            return ResponseEntity.ok().body(
                Map.of("message", "Password reset successfully")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                Map.of("message", e.getMessage())
            );
        }
    }
}