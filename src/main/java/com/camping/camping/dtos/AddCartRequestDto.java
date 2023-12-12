package com.camping.camping.dtos;

import jakarta.validation.constraints.Positive;


public record AddCartRequestDto(
        @Positive
         Long productId,
        @Positive
         Long productFirstOptionId,
        @Positive
         Long productSecondOptionId,
        @Positive
         Integer quantity
) {}
