package com.abraham.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.abraham.curso.springboot.webapp.springbootweb.models.dto.ParamDTO;
import com.abraham.curso.springboot.webapp.springbootweb.models.dto.ParamMixDTO;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("/foo")
    public ParamDTO foo(@RequestParam(required = false, defaultValue = "Hola que tal?") String message) {
        ParamDTO param = new ParamDTO();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/bar")
    public ParamMixDTO bar(@RequestParam(required = false) String text,
            @RequestParam(required = false) Integer code) {
        ParamMixDTO param = new ParamMixDTO();
        param.setMessage(text);
        param.setCode(code);
        return param;
    }

    @GetMapping("/request")
    public ParamMixDTO request(HttpServletRequest request) {
        ParamMixDTO params = new ParamMixDTO();
        params.setCode(Integer.parseInt(request.getParameter("code")));
        params.setMessage(request.getParameter("message"));
        return params;
    }
}
