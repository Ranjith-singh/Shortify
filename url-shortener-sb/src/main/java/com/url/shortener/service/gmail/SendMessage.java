package com.url.shortener.service.gmail;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.url.shortener.dtos.EmailRequest;
import com.url.shortener.service.email.CreateEmail;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Base64;

/* Class to demonstrate the use of Gmail Send Message API */
public class SendMessage {
    /**
     * @return the sent message, {@code null} otherwise.
     * @throws Exception 
     */
    // public static Message sendEmail(EmailRequest emailRequest) throws Exception {
            /* Load pre-authorized user credentials from the environment.
            TODO(developer) - See https://developers.google.com/identity for
                guides on implementing OAuth2 for your application.*/
            
        // String messageSubject = "Reset password Request.";
        // String bodyText = "";

        // // Encode as MIME message
        // Properties props = new Properties();
        // Session session = Session.getDefaultInstance(props, null);
        // MimeMessage email = new MimeMessage(session);
        // email.setFrom(new InternetAddress(fromEmailAddress));
        // email.addRecipient(javax.mail.Message.RecipientType.TO,
        //     new InternetAddress(toEmailAddress));
        // email.setSubject(messageSubject);
        // email.setText(bodyText);

        // // Encode and wrap the MIME message into a gmail message
        // ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        // email.writeTo(buffer);
        // byte[] rawMessageBytes = buffer.toByteArray();
        // String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        // Message message = new Message();
        // message.setRaw(encodedEmail);

        // MimeMessage email = CreateEmailCopy.createEmail(emailRequest);

        // Message message = CreateMessage.CreateMessageWithEmail(email);

        // try {
        //     // Create send message
        //     Gmail service = GmailService.getService();
        //     message = service.users().messages().send("me", message).execute();
        //     System.out.println("Message id: " + message.getId());
        //     System.out.println(message.toPrettyString());
        //     return message;
        // } catch (GoogleJsonResponseException e) {
        //     // TODO(developer) - handle error appropriately
        //     GoogleJsonError error = e.getDetails();
        //     if (error.getCode() == 403) {
        //         System.err.println("Unable to send message: " + e.getDetails());
        //     } else {
        //         throw e;
        //     }
        // }
        // return null;
    // }
}
