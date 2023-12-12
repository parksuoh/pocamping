package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;


public record RegisterRequestDto(
        @NotBlank
         String name,
        @NotBlank
         String password
){}


