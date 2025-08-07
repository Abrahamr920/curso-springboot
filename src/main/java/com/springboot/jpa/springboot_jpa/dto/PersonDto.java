package com.springboot.jpa.springboot_jpa.dto;

public class PersonDto {

    String name;
    String lastName;

    public PersonDto(String name, String lastName) {
        this.lastName = lastName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "PersonDto [" + name + " " + lastName + "]";
    }

}
