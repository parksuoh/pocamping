package com.camping.camping.domains.vo;

public enum ReservationStatus {

    REQUEST("REQUEST"),
    CONFIRM("CONFIRM"),
    RESERVATION_CANCELED("RESERVATION_CANCELED");

    private final String value;

    ReservationStatus(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
