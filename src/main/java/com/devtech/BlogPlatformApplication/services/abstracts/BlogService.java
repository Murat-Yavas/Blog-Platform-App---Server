package com.devtech.BlogPlatformApplication.services.abstracts;

import com.devtech.BlogPlatformApplication.entities.Blog;
import com.devtech.BlogPlatformApplication.requests.BlogCreateRequest;
import com.devtech.BlogPlatformApplication.requests.BlogUpdateRequest;
import com.devtech.BlogPlatformApplication.responses.BlogResponse;

import java.util.List;

public interface BlogService {

    List<BlogResponse> getAllBlogs();

    List<BlogResponse> getAllBlogsByUser(Long userId);
    List<BlogResponse> getBlogsByTopic(String topic);
    Blog getOneBlog(Long blogId);
    Blog createOneBlog(BlogCreateRequest newBlog);
    Blog updateOneBlog(BlogUpdateRequest blog, Long blogId);
    void deleteOneBlog(Long blogId);
}
