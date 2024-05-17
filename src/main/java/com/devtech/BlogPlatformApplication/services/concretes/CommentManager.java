package com.devtech.BlogPlatformApplication.services.concretes;

import com.devtech.BlogPlatformApplication.entities.Blog;
import com.devtech.BlogPlatformApplication.entities.Comment;
import com.devtech.BlogPlatformApplication.entities.User;
import com.devtech.BlogPlatformApplication.exceptions.BlogNotFoundException;
import com.devtech.BlogPlatformApplication.exceptions.CommentNotFoundException;
import com.devtech.BlogPlatformApplication.repositories.CommentRepository;
import com.devtech.BlogPlatformApplication.requests.CommentCreateRequest;
import com.devtech.BlogPlatformApplication.requests.CommentUpdateRequest;
import com.devtech.BlogPlatformApplication.responses.CommentResponse;
import com.devtech.BlogPlatformApplication.services.abstracts.BlogService;
import com.devtech.BlogPlatformApplication.services.abstracts.CommentService;
import com.devtech.BlogPlatformApplication.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentManager implements CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private BlogService blogService;

    @Override
    public List<CommentResponse> getAllCommentsByBlog(Long blogId) {
        List<Comment> list = commentRepository.findCommentsByBlogId(blogId);
        return list.stream().map(l -> {
            User user = userService.getOneUserById(l.getUser().getId());
            Blog blog = blogService.getOneBlog(l.getBlog().getId());
            return new CommentResponse(l, user, blog);
        }).collect(Collectors.toList());
    }

    @Override
    public Comment createOneComment(CommentCreateRequest newComment) {
        User user = userService.getOneUserById(newComment.getUserId());
        Blog blog = blogService.getOneBlog(newComment.getBlogId());

        if(user != null && blog != null) {
            Comment comment = new Comment();
            comment.setId(newComment.getId());
            comment.setCommentText(newComment.getCommentText());
            comment.setCreateDate(new Date());
            comment.setBlog(blog);
            comment.setUser(user);
            return commentRepository.save(comment);
        } else return null;
    }

    @Override
    public Comment updateOneComment(Long commentId, CommentUpdateRequest comment) {
        Optional<Comment> foundComment = commentRepository.findById(commentId);

        if(foundComment.isPresent()) {
            Comment commentToUpdate = foundComment.get();
            commentToUpdate.setCommentText(comment.getCommentText());
            return commentRepository.save(commentToUpdate);
        } else throw new CommentNotFoundException("The comment with id: " + commentId + " not found");
    }

    @Override
    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
