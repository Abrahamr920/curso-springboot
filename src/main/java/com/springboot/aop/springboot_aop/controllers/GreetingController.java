package com.springboot.aop.springboot_aop.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.aop.springboot_aop.services.GreetingService;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greetting")
    public ResponseEntity<?> greetting() {
        return ResponseEntity
                .ok(Collections.singletonMap("Greeting", greetingService.sayHello("Usuario", "¡Bienvenido!")));
    }

    @GetMapping("/greettingError")
    public ResponseEntity<?> greettingError() {
        return ResponseEntity
                .ok(Collections.singletonMap("Greeting", greetingService.sayHelloError("Usuario", "¡Bienvenido!")));
    }

}
