package com.abraham.curso.springboot.error.springboot_error.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abraham.curso.springboot.error.springboot_error.models.domain.User;
import com.abraham.curso.springboot.error.springboot_error.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    UserService service;

    @GetMapping
    public String index() {
        // int value = 10 / 0;
        int value = Integer.parseInt("10x");
        System.out.println("value = " + value);
        return "ok 200";
    }

    @GetMapping("/{value}")
    public String index(@PathVariable String value) {
        int parsedValue = Integer.parseInt(value);
        System.out.println("value = " + parsedValue);
        return "ok 200";
    }

    @GetMapping("/show")
    public List<User> show() {
        return service.findAll();
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable(name = "id") Long id) {

        // User user = service.findById(id).orElseThrow(() -> new
        // UserNotFoundException("Error, el usuario no existe!"));
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isEmpty()) {
            // throw new UserNotFoundException("Error, el usuario no existe!");
            return ResponseEntity.notFound().build();
        }
        // System.out.println("user = " + user.getLastname());
        return ResponseEntity.ok(userOptional.orElseThrow());
    }
}
