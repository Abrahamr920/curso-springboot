package com.abraham.springboot_jpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abraham.springboot_jpa.services.ProductService;

import jakarta.validation.ConstraintValidator;

@Component
public class IsExistDbValidation implements ConstraintValidator<IsExistDb, String> {

    @Autowired
    private ProductService service;

    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        return !service.existsBySku(value);
    }

}
