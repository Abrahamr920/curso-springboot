package com.abraham.curso.springboot.webapp.springbootweb.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abraham.curso.springboot.webapp.springbootweb.models.User;
import com.abraham.curso.springboot.webapp.springbootweb.models.dto.UserDTO;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details")
    public UserDTO details() {
        UserDTO userDto = new UserDTO();
        User user = new User("Abraham", "Rodriguez",null);
        userDto.setUser(user);
        userDto.setTitle("Primer App con SpringBoot");
        return userDto;
    }

    @GetMapping("/list")
    public List<User> userList() {
        List<User> list = new ArrayList<User>();
        list.add(new User("Mario", "Duran",null));
        list.add(new User("Marcela", "Pineda",null));
        list.add(new User("Nayib", "Bukele",null));
        return list;
    }

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap() {
        Map<String, Object> body = new HashMap<>();
        User user = new User("Abraham", "Rodriguez",null);
        body.put("title", "Primer App con SpringBoot");
        body.put("user", user);
        return body;
    }

}