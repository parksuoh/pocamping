package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;


public record AddCategoryRequestDto(
        @NotBlank
        String categoryName
) {}



//public class AddCategoryRequestDto {
//    @NotBlank
//    private String categoryName;
//
//    public AddCategoryRequestDto(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//}
