package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.List;


public record AddOrderRequestDto(
        @NotBlank
        String receiverName,
        @NotBlank
        String address,

         List<Long> cartItemIds
) {}


