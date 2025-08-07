package com.abraham.curso.springboot.error.springboot_error.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abraham.curso.springboot.error.springboot_error.models.domain.User;
import com.abraham.curso.springboot.error.springboot_error.services.UserService;

@Controller
public class VistaController {
    @Autowired
    UserService service;

    @GetMapping("/usersTable")
    public String getAllUsers(Model model) {
        List<User> users = service.findAll();
        model.addAttribute("users", users); // Añadimos la lista de usuarios al modelo
        return "users"; // Nombre de la vista que se va a renderizar (users.html)
    }
}
