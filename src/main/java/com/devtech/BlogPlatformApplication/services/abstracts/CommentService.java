package com.devtech.BlogPlatformApplication.services.abstracts;

import com.devtech.BlogPlatformApplication.entities.Comment;
import com.devtech.BlogPlatformApplication.requests.CommentCreateRequest;
import com.devtech.BlogPlatformApplication.requests.CommentUpdateRequest;
import com.devtech.BlogPlatformApplication.responses.CommentResponse;

import java.util.List;

public interface CommentService {

    List<CommentResponse> getAllCommentsByBlog(Long blogId);

    Comment createOneComment(CommentCreateRequest newComment);

    Comment updateOneComment(Long commentId, CommentUpdateRequest comment);

    void deleteOneComment(Long commentId);
}
