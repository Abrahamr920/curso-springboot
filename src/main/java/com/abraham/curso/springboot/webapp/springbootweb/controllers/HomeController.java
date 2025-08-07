package com.abraham.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({ "", "/", "/home" })
    public String home() {
        return "forward:/list";//redirect redirige y recarga la apgina y la barra de url mientras que forward no cambia la url
    }
}
