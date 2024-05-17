package com.devtech.BlogPlatformApplication.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Blog> blog;

    @OneToMany(mappedBy = "user")
    private List<Comment> comment;
}
