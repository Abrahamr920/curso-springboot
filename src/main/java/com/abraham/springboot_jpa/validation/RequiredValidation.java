package com.abraham.springboot_jpa.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;

public class RequiredValidation implements ConstraintValidator<IsRequired, String> {

    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        // return (value != null && !value.isBlank());
        return StringUtils.hasText(value);
    }

}