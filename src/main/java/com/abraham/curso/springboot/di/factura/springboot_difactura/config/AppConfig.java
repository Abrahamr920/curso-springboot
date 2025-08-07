package com.abraham.curso.springboot.di.factura.springboot_difactura.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.abraham.curso.springboot.di.factura.springboot_difactura.models.Item;
import com.abraham.curso.springboot.di.factura.springboot_difactura.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8") // Load the properties file
public class AppConfig {

    private List<Product> listOfProducts;

    @Bean
    @SuppressWarnings("unused")
    // @RequestScope
    List<Item> itemsInvoiceJson() {
        loadListOfProducts();

        List<Item> items = new ArrayList<>();
        for (Product p : listOfProducts) {
            int numeroAleatorio = (int) (Math.random() * 5) + 1;
            items.add(new Item(p, numeroAleatorio));
        }
        return items;
    }

    @Bean()
    @Primary
    @SuppressWarnings("unused")
    List<Item> itemsInvoiceLocal() {
        Product p1 = new Product("Laptop", 1000);
        Product p2 = new Product("Mouse", 25);
        Product p3 = new Product("Keyboard", 45);
        Product p4 = new Product("Monitor", 150);
        return Arrays.asList(new Item(p1, 1), new Item(p2, 2), new Item(p3, 3), new Item(p4, 4));
    }

    private void loadListOfProducts() {
        Resource resource = new ClassPathResource("json/products.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            Product[] products = mapper.readValue(resource.getInputStream(), Product[].class);
            listOfProducts = Arrays.asList(products);
        } catch (IOException e) {
        }
    }
}