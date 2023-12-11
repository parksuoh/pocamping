package com.camping.camping.dtos;

public record GetCartItemByCartItemIdDto(
         Long productId,
         String productName,
         Long productPrice,

         Long productFirstOptionId,
         String productFirstOptionName,
         Long productFirstOptionPrice,

         Long productSecondOptionId,
         String productSecondOptionName,
         Long productSecondOptionPrice,
         Integer quantity
) {}

//public class GetCartItemByCartItemIdDto {
//
//    private Long productId;
//    private String productName;
//    private Long productPrice;
//
//    private Long productFirstOptionId;
//    private String productFirstOptionName;
//    private Long productFirstOptionPrice;
//
//    private Long productSecondOptionId;
//    private String productSecondOptionName;
//    private Long productSecondOptionPrice;
//    private Integer quantity;
//
//    public GetCartItemByCartItemIdDto(Long productId, String productName, Long productPrice, Long productFirstOptionId, String productFirstOptionName, Long productFirstOptionPrice, Long productSecondOptionId, String productSecondOptionName, Long productSecondOptionPrice, Integer quantity) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productFirstOptionId = productFirstOptionId;
//        this.productFirstOptionName = productFirstOptionName;
//        this.productFirstOptionPrice = productFirstOptionPrice;
//        this.productSecondOptionId = productSecondOptionId;
//        this.productSecondOptionName = productSecondOptionName;
//        this.productSecondOptionPrice = productSecondOptionPrice;
//        this.quantity = quantity;
//    }
//
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public Long getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(Long productPrice) {
//        this.productPrice = productPrice;
//    }
//
//    public Long getProductFirstOptionId() {
//        return productFirstOptionId;
//    }
//
//    public void setProductFirstOptionId(Long productFirstOptionId) {
//        this.productFirstOptionId = productFirstOptionId;
//    }
//
//    public String getProductFirstOptionName() {
//        return productFirstOptionName;
//    }
//
//    public void setProductFirstOptionName(String productFirstOptionName) {
//        this.productFirstOptionName = productFirstOptionName;
//    }
//
//    public Long getProductFirstOptionPrice() {
//        return productFirstOptionPrice;
//    }
//
//    public void setProductFirstOptionPrice(Long productFirstOptionPrice) {
//        this.productFirstOptionPrice = productFirstOptionPrice;
//    }
//
//    public Long getProductSecondOptionId() {
//        return productSecondOptionId;
//    }
//
//    public void setProductSecondOptionId(Long productSecondOptionId) {
//        this.productSecondOptionId = productSecondOptionId;
//    }
//
//    public String getProductSecondOptionName() {
//        return productSecondOptionName;
//    }
//
//    public void setProductSecondOptionName(String productSecondOptionName) {
//        this.productSecondOptionName = productSecondOptionName;
//    }
//
//    public Long getProductSecondOptionPrice() {
//        return productSecondOptionPrice;
//    }
//
//    public void setProductSecondOptionPrice(Long productSecondOptionPrice) {
//        this.productSecondOptionPrice = productSecondOptionPrice;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//}
