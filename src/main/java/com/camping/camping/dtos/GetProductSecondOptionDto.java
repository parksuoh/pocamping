package com.camping.camping.dtos;


public record GetProductSecondOptionDto(
         Long id,
         String name,
         Long addPrice
){}


//public class GetProductSecondOptionDto {
//
//    private Long id;
//    private String name;
//    private Long addPrice;
//
//    public GetProductSecondOptionDto(Long id, String name, Long addPrice) {
//        this.id = id;
//        this.name = name;
//        this.addPrice = addPrice;
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
//    public Long getAddPrice() {
//        return addPrice;
//    }
//
//    public void setAddPrice(Long addPrice) {
//        this.addPrice = addPrice;
//    }
//}
