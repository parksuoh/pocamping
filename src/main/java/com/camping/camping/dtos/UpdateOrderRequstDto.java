package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record UpdateOrderRequstDto(
        @Positive
         Long orderId,
        @NotBlank
         String orderStatus
) {}


