package com.abrahamr920.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.abrahamr920.springboot.di.app.springboot_di.models.Product;

@Repository("productFoo")
public class ProductRepositoryFoo implements ProductRepository {

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Laptop", 1000L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Laptop", 1000L);
    }

}
