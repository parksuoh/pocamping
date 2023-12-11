package com.camping.camping.dtos;

public record GetOrderItemDto(
         Long orderItemId,
         String productName,
         Long productPrice,
         String productFirstOptionName,
         Long productFirstOptionPrice,
         String productSecondOptionName,
         Long productSecondOptionPrice,
         Long unitPrice,
         Integer quantity,
         Long totalPrice
) {}


//public class GetOrderItemDto {
//
//    private Long orderItemId;
//    private String productName;
//    private Long productPrice;
//    private String productFirstOptionName;
//    private Long productFirstOptionPrice;
//    private String productSecondOptionName;
//    private Long productSecondOptionPrice;
//    private Long unitPrice;
//    private Integer quantity;
//    private Long totalPrice;
//
//    public GetOrderItemDto(Long orderItemId, String productName, Long productPrice, String productFirstOptionName, Long productFirstOptionPrice, String productSecondOptionName, Long productSecondOptionPrice, Long unitPrice, Integer quantity, Long totalPrice) {
//        this.orderItemId = orderItemId;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productFirstOptionName = productFirstOptionName;
//        this.productFirstOptionPrice = productFirstOptionPrice;
//        this.productSecondOptionName = productSecondOptionName;
//        this.productSecondOptionPrice = productSecondOptionPrice;
//        this.unitPrice = unitPrice;
//        this.quantity = quantity;
//        this.totalPrice = totalPrice;
//    }
//
//
//    public Long getOrderItemId() {
//        return orderItemId;
//    }
//
//    public void setOrderItemId(Long orderItemId) {
//        this.orderItemId = orderItemId;
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
//    public Long getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(Long unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Long getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(Long totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//}


