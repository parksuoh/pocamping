package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;


public record LoginRequestDto(
        @NotBlank
         String name,
        @NotBlank
         String password
){}

