package com.camping.camping.dtos;

import jakarta.validation.constraints.Positive;



public record CartItemIdsDto(
        @Positive
        Long cartItemId
){}
