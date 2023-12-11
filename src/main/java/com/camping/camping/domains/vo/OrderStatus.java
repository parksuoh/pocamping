package com.camping.camping.domains.vo;

public enum OrderStatus {

    READY("READY"),
    DELIVERY("DELIVERY"),
    COMPLETE("COMPLETE"),
    CANCELED("CANCELED");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
