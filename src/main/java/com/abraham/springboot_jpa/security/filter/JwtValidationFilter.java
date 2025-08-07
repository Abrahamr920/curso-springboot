package com.abraham.springboot_jpa.security.filter;

import static com.abraham.springboot_jpa.security.TokenJwtConfig.AUTHORITIES;
import static com.abraham.springboot_jpa.security.TokenJwtConfig.CONTENT_TYPE;
import static com.abraham.springboot_jpa.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.abraham.springboot_jpa.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.abraham.springboot_jpa.security.TokenJwtConfig.PUBLIC_ENDPOINTS;
import static com.abraham.springboot_jpa.security.TokenJwtConfig.SECRET_KEY;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.abraham.springboot_jpa.security.SimpleGrantedAuthorityJsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 🔹 Verificar si la solicitud ya fue manejada por Spring (es decir, si no se
        // lanzó un error 404)
        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            chain.doFilter(request, response);
            return;
        }

        if (PUBLIC_ENDPOINTS.contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            response.getWriter().write(
                    "{\"error\": \"No autenticado: Debes proporcionar un token válido para acceder a este recurso.\"}");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(CONTENT_TYPE);

            return;
        }
        String token = header.replace(PREFIX_TOKEN, "");
        try {
            Claims claims = (Claims) Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            // String username = claims.getSubject(); //Otra forma de obtenerlo
            String username = (String) claims.get("username");
            Object authoritiesClaims = claims.get(AUTHORITIES);

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
                    .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                    .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                    null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (JwtException e) {
            Map<String, String> body = new HashMap<>();
            body.put("message", "Token JWT invalido.");
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(CONTENT_TYPE);
        }

    }

}
