package com.devtech.BlogPlatformApplication.requests;

import lombok.Data;

import java.util.Date;

@Data
public class BlogCreateRequest {

    private Long id;
    private String topic;
    private String title;
    private String content;
    private Long userId;
}
