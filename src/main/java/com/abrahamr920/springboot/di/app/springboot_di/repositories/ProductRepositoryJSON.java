package com.abrahamr920.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.abrahamr920.springboot.di.app.springboot_di.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductRepositoryJSON implements ProductRepository {

    List<Product> list;

    public ProductRepositoryJSON() {
        Resource resource = new ClassPathResource("json/products.json");
        readValueJson(resource);
    }

    public ProductRepositoryJSON(Resource resource) {
        readValueJson(resource);
    }

    private void readValueJson(Resource resource) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Product[] products = mapper.readValue(resource.getInputStream(), Product[].class);
            list = Arrays.asList(products);
        } catch (IOException e) {
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
