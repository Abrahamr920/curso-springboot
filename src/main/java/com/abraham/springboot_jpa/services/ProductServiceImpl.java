package com.abraham.springboot_jpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abraham.springboot_jpa.entities.Product;
import com.abraham.springboot_jpa.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setSku(product.getSku());
            System.out.println("Llego aquí return productRepository.save(existingProduct);");
            return productRepository.save(existingProduct);
        });
    }

    @Transactional
    @Override
    public Optional<Product> deleteById(Long id) {
        return productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return product;
        });
    }

    @Transactional
    @Override
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }

}
