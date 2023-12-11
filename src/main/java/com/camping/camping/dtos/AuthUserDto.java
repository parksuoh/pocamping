package com.camping.camping.dtos;


public record AuthUserDto(
         String name,
         String role,
         String accessToken
) {}


//public class AuthUserDto {
//
//    private String name;
//    private String role;
//    private String accessToken;
//
//    public AuthUserDto(String name, String role, String accessToken) {
//        this.name = name;
//        this.role = role;
//        this.accessToken = accessToken;
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
//
//    public String getAccessToken() {
//        return accessToken;
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//}