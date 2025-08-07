package com.abraham.springboot_jpa.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.abraham.springboot_jpa.exceptions.CustomAccessDeniedHandler;
import com.abraham.springboot_jpa.security.filter.JwtAuthenticationFilter;
import com.abraham.springboot_jpa.security.filter.JwtValidationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new CustomAccessDeniedHandler()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFiler() {
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;

    }
}
// return http
// .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF
// .sessionManagement(session ->
// session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// .authorizeHttpRequests(authz -> authz
// .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
// .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
// .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
// .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
// .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
// .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/{}")
// .hasAnyRole("ADMIN", "USER")
// .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
// .requestMatchers(HttpMethod.DELETE, "/api/products/{}").hasRole("ADMIN")
// .requestMatchers(HttpMethod.PUT, "/api/products/{}").hasRole("ADMIN")
// )
// .addFilter(new JwtValidationFilter(authenticationManager()))
// .addFilter(new JwtAuthenticationFilter(authenticationManager()))
// .exceptionHandling(exception ->
// exception.accessDeniedHandler(accessDeniedHandler))
// .build();