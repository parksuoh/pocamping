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


//public class AddCartRequestDto {
//
//    @Positive
//    private Long productId;
//    @Positive
//    private Long productFirstOptionId;
//    @Positive
//    private Long productSecondOptionId;
//    @Positive
//    private Integer quantity;
//
//    public AddCartRequestDto(Long productId, Long productFirstOptionId, Long productSecondOptionId, Integer quantity) {
//        this.productId = productId;
//        this.productFirstOptionId = productFirstOptionId;
//        this.productSecondOptionId = productSecondOptionId;
//        this.quantity = quantity;
//    }
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    public Long getProductFirstOptionId() {
//        return productFirstOptionId;
//    }
//
//    public void setProductFirstOptionId(Long productFirstOptionId) {
//        this.productFirstOptionId = productFirstOptionId;
//    }
//
//    public Long getProductSecondOptionId() {
//        return productSecondOptionId;
//    }
//
//    public void setProductSecondOptionId(Long productSecondOptionId) {
//        this.productSecondOptionId = productSecondOptionId;
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
