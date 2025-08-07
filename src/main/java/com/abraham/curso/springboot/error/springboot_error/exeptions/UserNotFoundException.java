package com.abraham.curso.springboot.error.springboot_error.exeptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
