package com.devtech.BlogPlatformApplication.responses;

import lombok.Data;

@Data
public class AuthResponse {

    private String accessToken;
    private String message;
    private String username;
    private Long userId;
}
