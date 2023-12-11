package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


public record AddAdminSecondOptionRequestDto(
        @Positive
         Long productFirstOptionId,
        @NotBlank
         String name,
        @PositiveOrZero
         Long price
){}



//public class AddAdminSecondOptionRequestDto {
//
//    @Positive
//    private Long productFirstOptionId;
//    @NotBlank
//    private String name;
//    @PositiveOrZero
//    private Long price;
//
//    public AddAdminSecondOptionRequestDto(Long productId, String name, Long price) {
//        this.productFirstOptionId = productId;
//        this.name = name;
//        this.price = price;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getPrice() {
//        return price;
//    }
//
//    public void setPrice(Long price) {
//        this.price = price;
//    }
//}
