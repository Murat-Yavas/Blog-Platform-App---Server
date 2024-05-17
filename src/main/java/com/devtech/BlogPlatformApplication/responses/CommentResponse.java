package com.devtech.BlogPlatformApplication.responses;

import com.devtech.BlogPlatformApplication.entities.Blog;
import com.devtech.BlogPlatformApplication.entities.Comment;
import com.devtech.BlogPlatformApplication.entities.User;
import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {

    private Long id;
    private String commentText;
    private Date createDate;
    private Long userId;
    private Long blogId;
    private String username;

    public CommentResponse(Comment entity, User user, Blog blog) {
        this.id = entity.getId();
        this.commentText = entity.getCommentText();
        this.createDate = entity.getCreateDate();
        this.username = user.getUsername();
        this.userId = user.getId();
        this.blogId = blog.getId();
    }
}
