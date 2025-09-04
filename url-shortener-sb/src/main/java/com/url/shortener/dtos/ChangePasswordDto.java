package com.url.shortener.dtos;

import lombok.Data;

@Data
public class ChangePasswordDto {
    String token;
    String password;
}
