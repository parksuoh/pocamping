package com.camping.camping.dtos;

public record CartItemsResponseDto(
         Long cartItemId,
         Integer quantity,
         Long productId,
         String name,
         Long price,
         Long productFirstOptionId,
         String productFirstOptionName,
         Long firstAddPrice,
         Long productSecondOptionId,
         String productSecondOptionName,
         Long productSecondPrice,
         Long itemUnitPrice,
         Long itemTotalPrice
){}

//public class CartItemsResponseDto {
//
//    private Long cartItemId;
//    private Integer quantity;
//    private Long productId;
//    private String name;
//    private Long price;
//    private Long productFirstOptionId;
//    private String productFirstOptionName;
//    private Long firstAddPrice;
//    private Long productSecondOptionId;
//    private String productSecondOptionName;
//    private Long productSecondPrice;
//    private Long itemUnitPrice;
//    private Long itemTotalPrice;
//
//
//    public CartItemsResponseDto(Long cartItemId, Integer quantity, Long productId, String name, Long price, Long productFirstOptionId, String productFirstOptionName, Long firstAddPrice, Long productSecondOptionId, String productSecondOptionName, Long productSecondPrice, Long itemUnitPrice, Long itemTotalPrice) {
//        this.cartItemId = cartItemId;
//        this.quantity = quantity;
//        this.productId = productId;
//        this.name = name;
//        this.price = price;
//        this.productFirstOptionId = productFirstOptionId;
//        this.productFirstOptionName = productFirstOptionName;
//        this.firstAddPrice = firstAddPrice;
//        this.productSecondOptionId = productSecondOptionId;
//        this.productSecondOptionName = productSecondOptionName;
//        this.productSecondPrice = productSecondPrice;
//        this.itemUnitPrice = itemUnitPrice;
//        this.itemTotalPrice = itemTotalPrice;
//    }
//
//    public Long getCartItemId() {
//        return cartItemId;
//    }
//
//    public void setCartItemId(Long cartItemId) {
//        this.cartItemId = cartItemId;
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
//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
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
//    public Long getFirstAddPrice() {
//        return firstAddPrice;
//    }
//
//    public void setFirstAddPrice(Long firstAddPrice) {
//        this.firstAddPrice = firstAddPrice;
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
//    public Long getProductSecondPrice() {
//        return productSecondPrice;
//    }
//
//    public void setProductSecondPrice(Long productSecondPrice) {
//        this.productSecondPrice = productSecondPrice;
//    }
//
//    public Long getItemUnitPrice() {
//        return itemUnitPrice;
//    }
//
//    public void setItemUnitPrice(Long itemUnitPrice) {
//        this.itemUnitPrice = itemUnitPrice;
//    }
//
//    public Long getItemTotalPrice() {
//        return itemTotalPrice;
//    }
//
//    public void setItemTotalPrice(Long itemTotalPrice) {
//        this.itemTotalPrice = itemTotalPrice;
//    }
//}
