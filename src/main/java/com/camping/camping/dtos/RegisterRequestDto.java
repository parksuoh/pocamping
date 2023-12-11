package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;


public record RegisterRequestDto(
        @NotBlank
         String name,
        @NotBlank
         String password
){}


//public class RegisterRequestDto {
//
//    @NotBlank
//    private String name;
//    @NotBlank
//    private String password;
//
//    public RegisterRequestDto(String name, String password) {
//        this.name = name;
//        this.password = password;
//    }
//
//    public String getName() {
//
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
