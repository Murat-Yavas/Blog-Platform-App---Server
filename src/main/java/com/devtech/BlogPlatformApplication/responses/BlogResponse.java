package com.devtech.BlogPlatformApplication.responses;

import com.devtech.BlogPlatformApplication.entities.Blog;
import com.devtech.BlogPlatformApplication.entities.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BlogResponse {

    private Long id;
    private String topic;
    private String title;
    private String content;
    private Date createDate;
    private String username;
    private Long userId;
    private List<CommentResponse> comment;

    public BlogResponse(Blog entity, User user, List<CommentResponse> commentResponse) {
        this.id = entity.getId();
        this.topic = entity.getTopic();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createDate = entity.getCreateDate();
        this.username = user.getUsername();
        this.userId = user.getId();
        this.comment = commentResponse;
    }
}
