package com.abraham.springboot_jpa.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.abraham.springboot_jpa.entities.Product;

@Component
public class ProductValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        // Validar que el nombre no esté vacío y tenga entre 3 y 20 caracteres
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            errors.rejectValue("name", null, "campo obligatorio");
        } else if (product.getName().length() < 3) {
            errors.rejectValue("name", null, "debe tener al menos 3 caracteres");
        } else if (product.getName().length() > 20) {
            errors.rejectValue("name", null, "debe tener máximo 20 caracteres");
        }
        // Validar que el precio no sea nulo y sea mayor o igual a 500
        if (product.getPrice() == null) {
            errors.rejectValue("price", null, "campo obligatorio");
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price", null, "debe ser mayor o igual a 500");
        }

        // Validar que la descripción no esté vacía
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", null, "campo obligatorio");
    }

}
