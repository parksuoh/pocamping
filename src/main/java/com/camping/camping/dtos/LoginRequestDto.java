package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;


public record LoginRequestDto(
        @NotBlank
         String name,
        @NotBlank
         String password
){}


//public class LoginRequestDto {
//
//    @NotBlank
//    private String name;
//    @NotBlank
//    private String password;
//
//    public LoginRequestDto(String name, String password) {
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
