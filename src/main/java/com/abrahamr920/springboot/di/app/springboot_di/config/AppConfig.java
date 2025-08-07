package com.abrahamr920.springboot.di.app.springboot_di.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.annotation.RequestScope;

import com.abrahamr920.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.abrahamr920.springboot.di.app.springboot_di.repositories.ProductRepositoryJSON;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpath:json/products.json")
    private Resource resource;

    @Bean("productJson")
    @SuppressWarnings("unused")
    @RequestScope
    ProductRepository productRepositoryJSON() {
        return new ProductRepositoryJSON(resource);
    }
}
