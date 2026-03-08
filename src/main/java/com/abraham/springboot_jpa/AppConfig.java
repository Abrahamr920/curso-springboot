package com.abraham.springboot_jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abraham.springboot_jpa.entities.Product;
import com.abraham.springboot_jpa.entities.Role;
import com.abraham.springboot_jpa.entities.User;
import com.abraham.springboot_jpa.repositories.ProductRepository;
import com.abraham.springboot_jpa.repositories.RoleRepository;
import com.abraham.springboot_jpa.repositories.UserRepository;

@Configuration
@PropertySource("classpath:messages.properties")
public class AppConfig {

    @Bean
    CommandLineRunner initData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            ProductRepository productRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            Role roleUser = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ROLE_USER");
                        return roleRepository.save(role);
                    });

            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ROLE_ADMIN");
                        return roleRepository.save(role);
                    });

            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("12345"));
                admin.setEnabled(true);
                admin.addRole(roleUser);
                admin.addRole(roleAdmin);
                userRepository.save(admin);
            }

            if (!userRepository.existsByUsername("usuario")) {
                User user = new User();
                user.setUsername("usuario");
                user.setPassword(passwordEncoder.encode("12345"));
                user.setEnabled(true);
                user.addRole(roleUser);
                userRepository.save(user);
            }

            if (!productRepository.existsBySku("SKU-001")) {
                productRepository.save(Product.builder()
                        .name("Teclado Mecanico")
                        .price(850)
                        .description("Teclado RGB con switch blue")
                        .sku("SKU-001")
                        .build());
            }

            if (!productRepository.existsBySku("SKU-002")) {
                productRepository.save(Product.builder()
                        .name("Mouse Gamer")
                        .price(620)
                        .description("Mouse ergonomico con 7 botones")
                        .sku("SKU-002")
                        .build());
            }

            if (!productRepository.existsBySku("SKU-003")) {
                productRepository.save(Product.builder()
                        .name("Monitor 24")
                        .price(1800)
                        .description("Monitor Full HD de 24 pulgadas")
                        .sku("SKU-003")
                        .build());
            }
        };
    }
}
