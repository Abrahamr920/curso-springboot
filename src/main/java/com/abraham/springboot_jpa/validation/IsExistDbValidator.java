package com.abraham.springboot_jpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abraham.springboot_jpa.services.ProductService;

import jakarta.validation.ConstraintValidator;

@Component
public class IsExistDbValidator implements ConstraintValidator<IsExistDb, String> {

    @Autowired
    private ProductService service;

    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        if (service == null) {
            return true;
        }
        return !service.existsBySku(value);
    }

}
