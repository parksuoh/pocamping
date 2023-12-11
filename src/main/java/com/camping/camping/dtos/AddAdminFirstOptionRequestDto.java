package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record AddAdminFirstOptionRequestDto(
        @Positive
        Long productId,
        @NotBlank
        String name,
        @PositiveOrZero
        Long price
) {

}






//public class AddAdminFirstOptionRequestDto {
//
//    @Positive
//    private Long productId;
//    @NotBlank
//    private String name;
//    @PositiveOrZero
//    private Long price;
//
//    public AddAdminFirstOptionRequestDto(Long productId, String name, Long price) {
//        this.productId = productId;
//        this.name = name;
//        this.price = price;
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





