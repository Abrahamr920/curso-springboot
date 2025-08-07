package com.abraham.springboot_jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.abraham.springboot_jpa.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    boolean existsBySku(String sku);
}
