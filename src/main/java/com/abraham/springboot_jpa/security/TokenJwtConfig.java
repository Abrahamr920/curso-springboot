package com.abraham.springboot_jpa.security;

import java.util.List;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

public class TokenJwtConfig {

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final long HOUR = 3600000; // 1 hour in milliseconds
    public static final String CONTENT_TYPE = "application/json";
    public static final String AUTHORITIES = "authorities";
    public static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/api/users",
            "/api/users/register",
            "/index.html",
            "/templates/listOfUsers.html");
}
