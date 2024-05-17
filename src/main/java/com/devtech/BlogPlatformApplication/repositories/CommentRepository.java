package com.devtech.BlogPlatformApplication.repositories;

import com.devtech.BlogPlatformApplication.entities.Comment;
import com.devtech.BlogPlatformApplication.responses.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment WHERE blog_id = :blogId", nativeQuery = true)
    List<Comment> findCommentsByBlogId(@Param("blogId") Long blogId);

    List<Comment> findCommentsByUserId(Long userId);
}
