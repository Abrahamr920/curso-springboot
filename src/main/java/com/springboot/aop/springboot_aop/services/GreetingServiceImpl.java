package com.springboot.aop.springboot_aop.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHello(String person, String phrase) {
        return person + " " + phrase;
    }

    @Override
    public String sayHelloError(String person, String phrase) {
        throw new RuntimeException("Error al saludar a " + person);
    }

}
