package com.devtech.BlogPlatformApplication.controllers;

import com.devtech.BlogPlatformApplication.entities.Comment;
import com.devtech.BlogPlatformApplication.requests.CommentCreateRequest;
import com.devtech.BlogPlatformApplication.requests.CommentUpdateRequest;
import com.devtech.BlogPlatformApplication.responses.CommentResponse;
import com.devtech.BlogPlatformApplication.services.concretes.CommentManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
@CrossOrigin("*")
public class CommentController {

    private CommentManager commentManager;

    @GetMapping("/{blogId}")
    public List<CommentResponse> receiveAllCommentsByBlog(@PathVariable Long blogId) {
        return commentManager.getAllCommentsByBlog(blogId);
    }

    @PostMapping
    public Comment addOneComment(@RequestBody CommentCreateRequest request) {
        return commentManager.createOneComment(request);
    }

    @PutMapping("/{commentId}")
    public Comment editOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request) {
        return commentManager.updateOneComment(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
        commentManager.deleteOneComment(commentId);
    }

}
