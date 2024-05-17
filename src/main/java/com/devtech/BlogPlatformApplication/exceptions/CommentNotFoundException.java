package com.devtech.BlogPlatformApplication.exceptions;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String message) {
        super(message);
    }
}
