package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.List;


public record AddOrderRequestDto(
        @NotBlank
        String receiverName,
        @NotBlank
        String address,

         List<CartItemIdsDto> cartItemIds
) {}


//public class AddOrderRequestDto {
//
//    @NotBlank
//    private String receiverName;
//    @NotBlank
//    private String address;
//
//    private List<CartItemIdsDto> cartItemIds;
//
//    public AddOrderRequestDto(String receiverName, String address, List<CartItemIdsDto> cartItemIds) {
//        this.receiverName = receiverName;
//        this.address = address;
//        this.cartItemIds = cartItemIds;
//    }
//
//
//    public String getReceiverName() {
//        return receiverName;
//    }
//
//    public void setReceiverName(String receiverName) {
//        this.receiverName = receiverName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public List<CartItemIdsDto> getCartItemIds() {
//        return cartItemIds;
//    }
//
//    public void setCartItemIds(List<CartItemIdsDto> cartItemIds) {
//        this.cartItemIds = cartItemIds;
//    }
//}
