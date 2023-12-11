package com.camping.camping.dtos;


import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.SecondOptionName;

public record GetCartItemsDto(
         Long cartItemId,
         Integer quantity,
         Long productId,
         Name name,
         Long price,
         Long productFirstOptionId,
         FirstOptionName productFirstOptionName,
         Long productFirstAddPrice,
         Long productSecondOptionId,
         SecondOptionName productSecondOptionName,
         Long productSecondAddPrice
){}


//public class GetCartItemsDto {
//
//    private Long cartItemId;
//    private Integer quantity;
//    private Long productId;
//    private Name name;
//    private Long price;
//    private Long productFirstOptionId;
//    private FirstOptionName productFirstOptionName;
//    private Long productFirstAddPrice;
//    private Long productSecondOptionId;
//    private SecondOptionName productSecondOptionName;
//    private Long productSecondAddPrice;
//
//    public GetCartItemsDto(Long cartItemId, Integer quantity, Long productId, Name name, Long price, Long productFirstOptionId, FirstOptionName productFirstOptionName, Long productFirstAddPrice, Long productSecondOptionId, SecondOptionName productSecondOptionName, Long productSecondAddPrice) {
//        this.cartItemId = cartItemId;
//        this.quantity = quantity;
//        this.productId = productId;
//        this.name = name;
//        this.price = price;
//        this.productFirstOptionId = productFirstOptionId;
//        this.productFirstOptionName = productFirstOptionName;
//        this.productFirstAddPrice = productFirstAddPrice;
//        this.productSecondOptionId = productSecondOptionId;
//        this.productSecondOptionName = productSecondOptionName;
//        this.productSecondAddPrice = productSecondAddPrice;
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
//    public Name getName() {
//        return name;
//    }
//
//    public void setName(Name name) {
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
//    public FirstOptionName getProductFirstOptionName() {
//        return productFirstOptionName;
//    }
//
//    public void setProductFirstOptionName(FirstOptionName productFirstOptionName) {
//        this.productFirstOptionName = productFirstOptionName;
//    }
//
//    public Long getProductFirstAddPrice() {
//        return productFirstAddPrice;
//    }
//
//    public void setProductFirstAddPrice(Long productFirstAddPrice) {
//        this.productFirstAddPrice = productFirstAddPrice;
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
//    public SecondOptionName getProductSecondOptionName() {
//        return productSecondOptionName;
//    }
//
//    public void setProductSecondOptionName(SecondOptionName productSecondOptionName) {
//        this.productSecondOptionName = productSecondOptionName;
//    }
//
//    public Long getProductSecondAddPrice() {
//        return productSecondAddPrice;
//    }
//
//    public void setProductSecondAddPrice(Long productSecondAddPrice) {
//        this.productSecondAddPrice = productSecondAddPrice;
//    }
//}
