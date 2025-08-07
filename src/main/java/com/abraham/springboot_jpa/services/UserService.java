package com.abraham.springboot_jpa.services;

import java.util.List;

import com.abraham.springboot_jpa.entities.User;

public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
