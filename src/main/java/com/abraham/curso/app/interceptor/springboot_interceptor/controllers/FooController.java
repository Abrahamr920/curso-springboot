package com.abraham.curso.app.interceptor.springboot_interceptor.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class FooController {

    @GetMapping("/foo")
    public Map<String, String> foo() {
        // return Map.of("foo", "foo");
        // System.out.println("Handler foo Controller");
        return Collections.singletonMap("message", "Handler foo Controller");
    }

    @GetMapping("/bar")
    public Map<String, String> bar() {
        // return Map.of("foo", "foo");
        return Collections.singletonMap("message", "Handler bar Controller");
    }

    @GetMapping("/baz")
    public Map<String, String> baz() {
        // return Map.of("foo", "foo");
        return Collections.singletonMap("message", "Handler baz Controller");
    }

}
