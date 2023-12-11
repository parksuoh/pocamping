package com.camping.camping.dtos;

import java.util.List;

public record GetProductByCategoryDto(
         Long id,
         String name,
         Long price,
         List<ProductImageDto> images
){}


//public class GetProductByCategoryDto {
//    private Long id;
//    private String name;
//    private Long price;
//    private List<ProductImageDto> images;
//
//    public GetProductByCategoryDto(Long id, String name, Long price, List<ProductImageDto> images) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.images = images;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
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
//    public List<ProductImageDto> getImages() {
//        return images;
//    }
//
//    public void setImages(List<ProductImageDto> images) {
//        this.images = images;
//    }
//}
