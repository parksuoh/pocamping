package com.camping.camping.dtos;

import java.util.List;

public class GetOrdersResponseDto {

    private Long orderId;
    private Long totalPrice;
    private String receiveName;
    private String sddress;
    private String orderStatus;

    private List<GetOrderItemDto> getOrderItemDtos;

    public GetOrdersResponseDto(Long orderId, Long totalPrice, String receiveName, String sddress, String orderStatus, List<GetOrderItemDto> getOrderItemDtos) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.receiveName = receiveName;
        this.sddress = sddress;
        this.orderStatus = orderStatus;
        this.getOrderItemDtos = getOrderItemDtos;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getSddress() {
        return sddress;
    }

    public void setSddress(String sddress) {
        this.sddress = sddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<GetOrderItemDto> getGetOrderItemDtos() {
        return getOrderItemDtos;
    }

    public void setGetOrderItemDtos(List<GetOrderItemDto> getOrderItemDtos) {
        this.getOrderItemDtos = getOrderItemDtos;
    }
}
