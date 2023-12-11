package com.camping.camping.dtos;

import java.util.List;


public record GetProductDetailDto(
         Long id,
         String name,
         Long price,
         String description,
         List<ProductImageDto> images,
         List<GetProductFitstOptionDto> firstOptions
) {}



//public class GetProductDetailDto {
//    private Long id;
//    private String name;
//    private Long price;
//    private String description;
//    private List<ProductImageDto> images;
//    private List<GetProductFitstOptionDto> firstOptions;
//
//    public GetProductDetailDto(Long id, String name, Long price, String description, List<ProductImageDto> images, List<GetProductFitstOptionDto> firstOptions) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//        this.images = images;
//        this.firstOptions = firstOptions;
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
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public List<ProductImageDto> getImages() {
//        return images;
//    }
//
//    public void setImages(List<ProductImageDto> images) {
//        this.images = images;
//    }
//
//    public List<GetProductFitstOptionDto> getFirstOptions() {
//        return firstOptions;
//    }
//
//    public void setFirstOptions(List<GetProductFitstOptionDto> firstOptions) {
//        this.firstOptions = firstOptions;
//    }
//}


