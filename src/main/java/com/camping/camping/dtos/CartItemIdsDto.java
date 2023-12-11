package com.camping.camping.dtos;

import jakarta.validation.constraints.Positive;



public record CartItemIdsDto(
        @Positive
        Long cartItemId
){}



//public class CartItemIdsDto {
//
//    @Positive
//    private Long cartItemId;
//
//    public CartItemIdsDto(Long cartItemId) {
//        this.cartItemId = cartItemId;
//    }
//
//    public Long getCartItemId() {
//        return cartItemId;
//    }
//
//    public void setCartItemId(Long cartItemId) {
//        this.cartItemId = cartItemId;
//    }
//}
