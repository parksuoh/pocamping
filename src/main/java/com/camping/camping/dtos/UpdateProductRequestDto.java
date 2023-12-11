package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


public record UpdateProductRequestDto(
        @Positive
        Long productId,

        @Positive
        Long categoryId,
        @NotBlank
         String name,
        @PositiveOrZero
         Long price,
        @NotNull
         String description
){}


//public class UpdateProductRequestDto {
//
//    @Positive
//    private Long productId;
//
//    @Positive
//    private Long categoryId;
//    @NotBlank
//    private String name;
//    @PositiveOrZero
//    private Long price;
//    @NotNull
//    private String discription;
//
//    public UpdateProductRequestDto(Long productId, Long categoryId, String name, Long price, String discription) {
//        this.productId = productId;
//        this.categoryId = categoryId;
//        this.name = name;
//        this.price = price;
//        this.discription = discription;
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
//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
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
//
//    public String getDiscription() {
//        return discription;
//    }
//
//    public void setDiscription(String discription) {
//        this.discription = discription;
//    }
//}
