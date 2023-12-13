package com.camping.camping.dtos;

import java.util.List;

public record GetOrdersResponseDto(
         Long orderId,
         Long totalPrice,
         String receiveName,
         String sddress,
         String orderStatus,
         List<GetOrderItemDto> orderItems
) {}
