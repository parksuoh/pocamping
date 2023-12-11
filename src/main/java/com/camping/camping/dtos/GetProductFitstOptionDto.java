package com.camping.camping.dtos;

import java.util.List;


public record GetProductFitstOptionDto(
         Long id,
         String name,
         Long addPrice,
         List<GetProductSecondOptionDto> secondOptions
){}


//public class GetProductFitstOptionDto {
//    private Long id;
//    private String name;
//    private Long addPrice;
//    private List<GetProductSecondOptionDto> secondOptions;
//
//    public GetProductFitstOptionDto(Long id, String name, Long addPrice, List<GetProductSecondOptionDto> secondOptions) {
//        this.id = id;
//        this.name = name;
//        this.addPrice = addPrice;
//        this.secondOptions = secondOptions;
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
//
//    public List<GetProductSecondOptionDto> getSecondOptions() {
//        return secondOptions;
//    }
//
//    public void setSecondOptions(List<GetProductSecondOptionDto> secondOptions) {
//        this.secondOptions = secondOptions;
//    }
//}
