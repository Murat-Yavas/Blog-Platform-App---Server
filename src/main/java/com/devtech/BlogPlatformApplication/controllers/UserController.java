package com.devtech.BlogPlatformApplication.controllers;

import com.devtech.BlogPlatformApplication.entities.User;
import com.devtech.BlogPlatformApplication.requests.UserUpdateRequest;
import com.devtech.BlogPlatformApplication.responses.UserResponse;
import com.devtech.BlogPlatformApplication.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    @GetMapping
    public List<UserResponse> receiveAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse receiveOneUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public User addOneUser(@RequestBody User newUser) {
        return userService.createOneUser(newUser);
    }

    @PutMapping("/{userId}")
    public User editOneUser(@RequestBody UserUpdateRequest user, @PathVariable Long userId) {
        return userService.updateOneUser(user, userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        userService.deleteOneUser(userId);
    }
}
