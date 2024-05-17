package com.devtech.BlogPlatformApplication.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {

    private Long id;
    private String commentText;
    private Long blogId;
    private Long userId;
}
