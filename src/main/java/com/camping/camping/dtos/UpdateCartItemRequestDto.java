package com.camping.camping.dtos;

import jakarta.validation.constraints.Positive;


public record UpdateCartItemRequestDto(
        @Positive
         Long cartItemId,

        @Positive
         Integer quantity
) {}

