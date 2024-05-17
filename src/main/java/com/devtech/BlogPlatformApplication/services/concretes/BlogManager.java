package com.devtech.BlogPlatformApplication.services.concretes;

import com.devtech.BlogPlatformApplication.entities.Blog;
import com.devtech.BlogPlatformApplication.entities.Comment;
import com.devtech.BlogPlatformApplication.entities.User;
import com.devtech.BlogPlatformApplication.exceptions.BlogNotFoundException;
import com.devtech.BlogPlatformApplication.exceptions.UserNotFoundException;
import com.devtech.BlogPlatformApplication.repositories.BlogRepository;
import com.devtech.BlogPlatformApplication.requests.BlogCreateRequest;
import com.devtech.BlogPlatformApplication.requests.BlogUpdateRequest;
import com.devtech.BlogPlatformApplication.responses.BlogResponse;
import com.devtech.BlogPlatformApplication.responses.CommentResponse;
import com.devtech.BlogPlatformApplication.services.abstracts.BlogService;
import com.devtech.BlogPlatformApplication.services.abstracts.CommentService;
import com.devtech.BlogPlatformApplication.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogManager implements BlogService {

    private final BlogRepository blogRepository;
    private final UserService userService;
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }


    @Override
    public List<BlogResponse> getAllBlogs() {
        List<Blog> list = blogRepository.findAll();
        return list.stream().map(l -> {
            User user = userService.getOneUserById(l.getUser().getId());
            List<CommentResponse> comment = commentService.getAllCommentsByBlog(l.getId());
            return new BlogResponse(l, user, comment);
        }).collect(Collectors.toList());
    }

    @Override
    public List<BlogResponse> getAllBlogsByUser(Long userId) {
        List<Blog> list = blogRepository.findBlogByUserId(userId);
        return list.stream().map(l -> {
            User user = userService.getOneUserById(l.getUser().getId());
            List<CommentResponse> comment = commentService.getAllCommentsByBlog(l.getId());
            return new BlogResponse(l, user, comment);
        }).collect(Collectors.toList());
    }

    @Override
    public List<BlogResponse> getBlogsByTopic(String topic) {
        List<Blog> list = blogRepository.findBlogsByTopic(topic);

        return list.stream().map(l -> {
            User user = userService.getOneUserById(l.getUser().getId());
            List<CommentResponse> comment = commentService.getAllCommentsByBlog(l.getId());
            return new BlogResponse(l, user, comment);
        }).collect(Collectors.toList());
    }

    @Override
    public Blog getOneBlog(Long blogId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if(blog.isPresent()) return blog.get();
        throw new BlogNotFoundException("The blog with id: " + blogId + "not found");
    }

    @Override
    public Blog createOneBlog(BlogCreateRequest blogRequest) {
        //aynı title'da blog olmasın kontrolü yap
        User user = userService.getOneUserById(blogRequest.getUserId());
        if(user != null) {
            Blog blogToSave = new Blog();
            blogToSave.setId(blogRequest.getId());
            blogToSave.setTopic(blogRequest.getTopic());
            blogToSave.setTitle(blogRequest.getTitle());
            blogToSave.setContent(blogRequest.getContent());
            blogToSave.setCreateDate(new Date());
            blogToSave.setUser(user);
            return blogRepository.save(blogToSave);
        } else throw new UserNotFoundException("The user with id: " + blogRequest.getUserId() + " not found");

    }

    @Override
    public Blog updateOneBlog(BlogUpdateRequest blogRequest, Long blogId) {
        User user = userService.getOneUserById(blogRequest.getUserId());
        Optional<Blog> blog = blogRepository.findById(blogId);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        if(blog.isPresent()) {
            Blog blogToUpdate = blog.get();
            blogToUpdate.setTopic(blogRequest.getTopic());
            blogToUpdate.setTitle(blogRequest.getTitle());
            blogToUpdate.setContent(blogRequest.getContent());
            blogToUpdate.setUser(user);
            return blogRepository.save(blogToUpdate);
        } else throw new BlogNotFoundException("The blog with id: " + blogId + " not found");
    }

    @Override
    public void deleteOneBlog(Long blogId) {
        blogRepository.deleteById(blogId);
    }
}
