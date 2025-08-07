package com.abraham.curso.springboot.webapp.springbootweb.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.abraham.curso.springboot.webapp.springbootweb.models.User;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {
        User user = new User("Abraham", "Rodriguez", "abrahamr920@gmail.com");
        model.addAttribute("title", "Primer App con SpringBoot");
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        List<User> users = userList();
        //List<User> users = new ArrayList<>();
        model.addAttribute("users", users);
        model.addAttribute("title", "Listado de Usuarios.");
        return "list";
    }

    @ModelAttribute("users")
    public List<User> userList() {
        return Arrays.asList(
                new User("Carlos", "Guzman", "carlos@gmail.com"),
                new User("Alfredo", "Palacios", "alfred@hotmail.com"),
                new User("Alfredo", "Palacios", null));
    }

}