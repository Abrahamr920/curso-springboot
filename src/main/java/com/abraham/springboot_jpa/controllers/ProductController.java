package com.abraham.springboot_jpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abraham.springboot_jpa.entities.Product;
import com.abraham.springboot_jpa.services.ProductService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // @Autowired
    // private ProductValidation validator;

    @GetMapping
    public List<?> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        // validator.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
        // validator.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return service.update(id, product).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.deleteById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping("/check-sku/{sku}")
    public ResponseEntity<?> checkSku(@Valid @PathVariable String sku) {
        boolean exists = service.existsBySku(sku);
        if (exists) {
            return ResponseEntity.badRequest().body("El SKU ya existe en la base de datos");
        }
        return ResponseEntity.ok("El SKU está disponible");
    }

}

// Versiones del profesor un poco menos optimizadas
// @GetMapping("/{id}")
// public ResponseEntity<?> view(@PathVariable Long id) {
// Optional<Product> optionalProduct = service.findById(id);
// if (optionalProduct.isPresent()) {
// return ResponseEntity.ok(optionalProduct.orElseThrow());
// }
// return ResponseEntity.notFound().build();
// }