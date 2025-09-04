package com.url.shortener.dtos;

import lombok.Data;

@Data
public class EmailRequest {
    String senderEmail;
    String receiverEmail;
    String subject;
    String body;
}
