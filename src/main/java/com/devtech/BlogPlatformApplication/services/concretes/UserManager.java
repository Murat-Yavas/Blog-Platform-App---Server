package com.devtech.BlogPlatformApplication.services.concretes;

import com.devtech.BlogPlatformApplication.entities.User;
import com.devtech.BlogPlatformApplication.exceptions.UserNotFoundException;
import com.devtech.BlogPlatformApplication.repositories.UserRepository;
import com.devtech.BlogPlatformApplication.requests.UserUpdateRequest;
import com.devtech.BlogPlatformApplication.responses.UserResponse;
import com.devtech.BlogPlatformApplication.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponse> getAllUsers() {
        List<User> list = userRepository.findAll();
        return list.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Override
    public User getOneUserById(Long userId) {
       Optional<User> foundUser = userRepository.findById(userId);
       if(foundUser.isPresent()) return foundUser.get();
       else throw new UserNotFoundException("The user with id: " + userId + " not found");
    }

    @Override
    public UserResponse getUserById(Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if(foundUser.isPresent()) return new UserResponse(foundUser.get());
        else throw new UserNotFoundException("The user with id: " + userId + " not found");
    }

    @Override
    public User createOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User updateOneUser(UserUpdateRequest user, Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if(foundUser.isEmpty()) return null;

        else {
            User userToUpdate = foundUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(userToUpdate);
        }
    }

    @Override
    public void deleteOneUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getOneUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
