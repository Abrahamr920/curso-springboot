package com.abraham.curso.springboot.error.springboot_error;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.abraham.curso.springboot.error.springboot_error.models.domain.User;

@Configuration
@PropertySource("classpath:data.properties")
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Bean
    public List<User> users() {
        return users;
    }
}
