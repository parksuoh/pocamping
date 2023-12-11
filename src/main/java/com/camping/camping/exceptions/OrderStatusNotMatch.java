package com.camping.camping.exceptions;

public class OrderStatusNotMatch extends RuntimeException {

    public OrderStatusNotMatch() {
        super("주문상태가 존재하지 않습니다.");
    }
}
