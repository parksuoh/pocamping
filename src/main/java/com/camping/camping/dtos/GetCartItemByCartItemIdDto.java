package com.camping.camping.dtos;

public record GetCartItemByCartItemIdDto(
         Long productId,
         String productName,
         Long productPrice,

         Long productFirstOptionId,
         String productFirstOptionName,
         Long productFirstOptionPrice,

         Long productSecondOptionId,
         String productSecondOptionName,
         Long productSecondOptionPrice,
         Integer quantity
) {}
