package com.camping.camping.dtos;

import java.util.List;


public record GetPlacesDto(
         Long placeId,

         String name,

         Long price,

         List<PlaceImageDto> placeImages
){}


//public class GetPlacesDto {
//
//    private Long placeId;
//
//    private String name;
//
//    private Long price;
//
//    private List<PlaceImageDto> placeImages;
//
//    public GetPlacesDto(Long placeId, String name, Long price, List<PlaceImageDto> placeImages) {
//        this.placeId = placeId;
//        this.name = name;
//        this.price = price;
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
//    public List<PlaceImageDto> getPlaceImages() {
//        return placeImages;
//    }
//
//    public void setPlaceImages(List<PlaceImageDto> placeImages) {
//        this.placeImages = placeImages;
//    }
//}
