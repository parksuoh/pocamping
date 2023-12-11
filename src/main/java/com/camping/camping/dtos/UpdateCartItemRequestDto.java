package com.camping.camping.dtos;

import jakarta.validation.constraints.Positive;


public record UpdateCartItemRequestDto(
        @Positive
         Long cartItemId,

        @Positive
         Integer quantity
) {}

//public class UpdateCartItemRequestDto {
//
//    @Positive
//    private Long cartItemId;
//
//    @Positive
//    private Integer quantity;
//
//    public UpdateCartItemRequestDto(Long cartItemId, Integer quantity) {
//        this.cartItemId = cartItemId;
//        this.quantity = quantity;
//    }
//
//    public Long getCartItemId() {
//        return cartItemId;
//    }
//
//    public void setCartItemId(Long cartItemId) {
//        this.cartItemId = cartItemId;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//}
