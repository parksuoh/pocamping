package com.camping.camping.dtos;

import java.util.List;

public record GetPlaceDetailResponseDto(
         Long placeId,

         String name,

         Long price,

         String description,

         List<PlaceImageDto> placeImages
){}

//public class GetPlaceDetailResponseDto {
//
//    private Long placeId;
//
//    private String name;
//
//    private Long price;
//
//    private String description;
//
//    private List<PlaceImageDto> placeImages;
//
//    public GetPlaceDetailResponseDto(Long placeId, String name, Long price, String description, List<PlaceImageDto> placeImages) {
//        this.placeId = placeId;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//        this.placeImages = placeImages;
//    }
//
//    public Long getPlaceId() {
//        return placeId;
//    }
//
//    public void setPlaceId(Long placeId) {
//        this.placeId = placeId;
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
//    public List<PlaceImageDto> getPlaceImages() {
//        return placeImages;
//    }
//
//    public void setPlaceImages(List<PlaceImageDto> placeImages) {
//        this.placeImages = placeImages;
//    }
//}
