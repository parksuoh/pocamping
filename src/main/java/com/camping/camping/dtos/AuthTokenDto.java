package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthTokenDto(
        @NotBlank
        String token
) {}

