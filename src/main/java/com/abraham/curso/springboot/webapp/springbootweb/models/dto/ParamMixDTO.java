package com.abraham.curso.springboot.webapp.springbootweb.models.dto;

public class ParamMixDTO {
    private Integer code;
    private String message;

    public ParamMixDTO() {
    }

    public ParamMixDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
