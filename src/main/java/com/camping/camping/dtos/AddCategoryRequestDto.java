package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;


public record AddCategoryRequestDto(
        @NotBlank
        String categoryName
) {}

