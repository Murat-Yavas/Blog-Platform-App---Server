package com.devtech.BlogPlatformApplication.requests;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String username;
    private String password;
}
