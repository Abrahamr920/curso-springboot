package com.abraham.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.abraham.springboot_jpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    // @EntityGraph(attributePaths = { "roles" })
    List<User> findAll();

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
