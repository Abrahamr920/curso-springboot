package com.abraham.springboot_jpa.exceptions;

import static com.abraham.springboot_jpa.security.TokenJwtConfig.CONTENT_TYPE;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(CONTENT_TYPE);

        PrintWriter writer = response.getWriter();
        writer.write("{\"error\": \"Acceso denegado: No tienes los permisos necesarios para este recurso.\"}");
        writer.flush();
    }
}
