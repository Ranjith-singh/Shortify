package com.url.shortener.service.email;

import java.util.Properties;
import jakarta.mail.MessagingException;

import jakarta.mail.Address;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import com.google.api.client.util.Value;
import com.url.shortener.dtos.EmailRequest;

public class CreateEmail {

    @Value("${app.base-url}")
    private static String appBaseUrl;
    
    @Value("${spring.mail.username}")
    private static String fromEmail;

    public static MimeMessage createEmail(EmailRequest emailRequest, String token) throws MessagingException{
            String toEmailAddress = emailRequest.getReceiverEmail();
            // String fromEmailAddress = emailRequest.getSenderEmail();
            String subject = emailRequest.getSubject();

            String resetLink =  emailRequest.getBody() + "/reset-password?token=" + token;
            String emailContent = buildEmailContent(resetLink);
            Properties props = new Properties();
            jakarta.mail.Session session = Session.getDefaultInstance(props, null);
            
            MimeMessage email = new MimeMessage(session);
            email.setFrom(fromEmail);
            email.addRecipients(jakarta.mail.Message.RecipientType.TO,toEmailAddress);
            email.setSubject(subject);
            email.setContent(emailContent, "text/html; charset=utf-8");
            return email;
    }

    private static String buildEmailContent(String resetLink) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .button { background-color: #4CAF50; color: #f5f2f2ff ; padding: 10px 20px; 
                        text-decoration: none; border-radius: 5px; display: inline-block; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>Password Reset Request</h2>
                    <p>You requested to reset your password. Click the button below to proceed:</p>
                    <p><a href="%s" class="button">Reset Password</a></p>
                    <p>If the button doesn't work, copy and paste this link into your browser:</p>
                    <p>%s</p>
                    <p>This link will expire in 15 minutes for security reasons.</p>
                    <p>If you didn't request this reset, please ignore this email.</p>
                </div>
            </body>
            </html>
            """.formatted(resetLink, resetLink);
    }
}
