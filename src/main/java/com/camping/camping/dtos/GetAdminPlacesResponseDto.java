package com.camping.camping.dtos;

public record GetAdminPlacesResponseDto(
         Long placeId,
         String name,
         Long price
) {}

//public class GetAdminPlacesResponseDto {
//
//    private Long placeId;
//    private String name;
//    private Long price;
//
//    public GetAdminPlacesResponseDto(Long placeId, String name, Long price) {
//        this.placeId = placeId;
//        this.name = name;
//        this.price = price;
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
//}
