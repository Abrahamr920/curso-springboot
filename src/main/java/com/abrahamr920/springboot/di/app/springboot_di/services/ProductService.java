package com.abrahamr920.springboot.di.app.springboot_di.services;

import java.util.List;

import com.abrahamr920.springboot.di.app.springboot_di.models.Product;

public interface ProductService {
    public List<Product> findAll();

    public Product findById(Long id);
}
