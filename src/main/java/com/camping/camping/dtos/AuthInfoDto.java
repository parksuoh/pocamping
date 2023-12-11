package com.camping.camping.dtos;

public record AuthInfoDto(
         String name,
         String role
) {}

//public class AuthInfoDto {
//    private String name;
//    private String role;
//
//    public AuthInfoDto(String name, String role) {
//        this.name = name;
//        this.role = role;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//}
