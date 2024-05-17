package com.devtech.BlogPlatformApplication.services.abstracts;

import com.devtech.BlogPlatformApplication.entities.User;
import com.devtech.BlogPlatformApplication.requests.UserUpdateRequest;
import com.devtech.BlogPlatformApplication.responses.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    User getOneUserById(Long userId);

    UserResponse getUserById (Long userId);
    User createOneUser(User newUser);
    User updateOneUser(UserUpdateRequest user, Long userId);
    void deleteOneUser(Long userId);

    User getOneUserByUsername(String username);
}
