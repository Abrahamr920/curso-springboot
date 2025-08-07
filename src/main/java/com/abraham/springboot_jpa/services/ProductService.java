package com.abraham.springboot_jpa.services;

import java.util.List;
import java.util.Optional;

import com.abraham.springboot_jpa.entities.Product;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> deleteById(Long id);

    boolean existsBySku(String sku);
}
