package com.abraham.curso.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abraham.curso.springboot.error.springboot_error.models.domain.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private List<User> users;

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {

        User user = users.stream().filter(p -> p.getId().equals(id.intValue())).findFirst().orElse(null);
        // if (user == null) {
        // throw new UserNotFoundException("Error, el usuario no existe!");
        // }
        return Optional.ofNullable(user);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
