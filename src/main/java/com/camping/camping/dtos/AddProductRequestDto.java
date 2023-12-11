package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;



public record AddProductRequestDto(
        @Positive
         Long categoryId,
        @NotBlank
         String name,
        @PositiveOrZero
         Long price,
        @NotNull
         String description
) {}




//public class AddProductRequestDto {
//    @Positive
//    private Long categoryId;
//    @NotBlank
//    private String name;
//    @PositiveOrZero
//    private Long price;
//    @NotNull
//    private String description;
//
//    public AddProductRequestDto(Long categoryId, String name, Long price, String description) {
//        this.categoryId = categoryId;
//        this.name = name;
//        this.price = price;
//        this.description = description;
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
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}
