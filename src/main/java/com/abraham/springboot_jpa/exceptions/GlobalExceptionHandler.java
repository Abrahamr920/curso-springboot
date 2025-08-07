package com.abraham.springboot_jpa.exceptions;

import static com.abraham.springboot_jpa.security.TokenJwtConfig.CONTENT_TYPE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja rutas no encontradas (Spring no tiene un controlador para la URL)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.NOT_FOUND.value());

        Map<String, String> body = new HashMap<>();
        body.put("error", "Recurso no encontrado");
        body.put("message", "La URL '" + request.getRequestURI() + "' no existe en el servidor.");

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));

        return;
    }

}
