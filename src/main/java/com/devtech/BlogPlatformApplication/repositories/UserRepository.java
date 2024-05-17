package com.devtech.BlogPlatformApplication.repositories;

import com.devtech.BlogPlatformApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
