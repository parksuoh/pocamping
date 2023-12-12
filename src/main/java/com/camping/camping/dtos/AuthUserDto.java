package com.camping.camping.dtos;


public record AuthUserDto(
         String name,
         String role,
         String accessToken
) {}
