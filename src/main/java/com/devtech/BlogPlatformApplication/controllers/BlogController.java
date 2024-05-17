package com.devtech.BlogPlatformApplication.controllers;

import com.devtech.BlogPlatformApplication.entities.Blog;
import com.devtech.BlogPlatformApplication.requests.BlogCreateRequest;
import com.devtech.BlogPlatformApplication.requests.BlogUpdateRequest;
import com.devtech.BlogPlatformApplication.responses.BlogResponse;
import com.devtech.BlogPlatformApplication.services.abstracts.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@AllArgsConstructor
@CrossOrigin("*")
public class BlogController {

    private BlogService blogService;

    @GetMapping
    public List<BlogResponse> receiveAllBlogs()  {
        return blogService.getAllBlogs();
    }

    @GetMapping("/users/{userId}")
    public List<BlogResponse> receiveAllBlogsByUser(@PathVariable Long userId) {
        return blogService.getAllBlogsByUser(userId);
    }

    @GetMapping("/topic/{topicName}")
    public List<BlogResponse> receiveBlogsByTopic(@PathVariable String topicName) {
        return blogService.getBlogsByTopic(topicName);
    }

    @GetMapping("/{blogId}")
    public Blog receiveOneBlog(@PathVariable Long blogId) {
        return blogService.getOneBlog(blogId);
    }

    @PostMapping
    public Blog addOneBlog(@RequestBody BlogCreateRequest request) {
        return blogService.createOneBlog(request);
    }

    @PutMapping("/{blogId}")
    public Blog editOneBlog(@RequestBody BlogUpdateRequest request, @PathVariable Long blogId) {
        return  blogService.updateOneBlog(request, blogId);
    }

    @DeleteMapping("/{blogId}")
    public void deleteOneBlog(@PathVariable Long blogId) {
        blogService.deleteOneBlog(blogId);
    }
}
