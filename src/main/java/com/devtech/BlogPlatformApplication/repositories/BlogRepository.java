package com.devtech.BlogPlatformApplication.repositories;

import com.devtech.BlogPlatformApplication.entities.Blog;
import com.devtech.BlogPlatformApplication.responses.BlogResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findBlogByUserId(Long userId);

    List<Blog> findBlogsByTopic(String topic);
}
