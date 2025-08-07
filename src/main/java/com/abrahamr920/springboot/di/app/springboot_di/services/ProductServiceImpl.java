package com.abrahamr920.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.abrahamr920.springboot.di.app.springboot_di.models.Product;
import com.abrahamr920.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    @Qualifier("productJson")
    private ProductRepository repository;
    @Value("${config.price.tax}")
    private double tax;

    @Autowired
    private Environment env;

    /*
     * public ProductServiceImpl(@Qualifier("foo")ProductRepository repository) {
     * this.repository = repository;
     * }
     */
    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            @SuppressWarnings("null")
            Double priceTax = p.getPrice() * env.getProperty("config.price.tax", Double.class);
            System.out.println(env.getProperty("config.price.tax", Double.class));
            p.setPrice(priceTax.longValue());
            return p;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        Product product = (Product) repository.findById(id).clone();
        Double priceTax = product.getPrice() * tax;
        product.setPrice(priceTax.longValue());
        return product;
    }

}
