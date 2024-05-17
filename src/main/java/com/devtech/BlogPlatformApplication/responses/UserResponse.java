package com.devtech.BlogPlatformApplication.responses;

import com.devtech.BlogPlatformApplication.entities.Blog;
import com.devtech.BlogPlatformApplication.entities.Comment;
import com.devtech.BlogPlatformApplication.entities.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponse {

    private Long id;
    private String username;
    private List<Blog> blog;
    private List<Comment> comment;


    public UserResponse(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.blog = entity.getBlog();
        this.comment = entity.getComment();
    }
}
