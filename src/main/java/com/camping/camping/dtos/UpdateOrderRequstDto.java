package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record UpdateOrderRequstDto(
        @Positive
         Long orderId,
        @NotBlank
         String orderStatus
) {}


//public class UpdateOrderRequstDto {
//
//    @Positive
//    private Long orderId;
//    @NotBlank
//    private String orderStatus;
//
//    public UpdateOrderRequstDto(Long orderId, String orderStatus) {
//        this.orderId = orderId;
//        this.orderStatus = orderStatus;
//    }
//
//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }
//
//    public String getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(String orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//}
