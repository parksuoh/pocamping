package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public record AddPlaceRequestDto(
        @NotBlank
        String name,
        @PositiveOrZero
        Long price,
        @NotNull
        String description
) {}

//public class AddPlaceRequestDto {
//
//    @NotBlank
//    private String name;
//    @PositiveOrZero
//    private Long price;
//    @NotNull
//    private String description;
//
//    public AddPlaceRequestDto(String name, Long price, String description) {
//        this.name = name;
//        this.price = price;
//        this.description = description;
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
