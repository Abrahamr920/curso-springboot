package com.abraham.curso.springboot.webapp.springbootweb.models.dto;

public class ParamDTO {
    private String message;

    public ParamDTO() {
    }

    public ParamDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
