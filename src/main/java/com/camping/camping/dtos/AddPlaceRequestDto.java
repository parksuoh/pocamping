package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public record AddPlaceRequestDto(
        @NotBlank
        String name,
        @PositiveOrZero
        Long price,
        @NotNull
        String description
) {}
