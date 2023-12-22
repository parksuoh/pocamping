package com.camping.camping.domains.vo;

import com.camping.camping.exceptions.PlaceReservationStatusNotMatch;

import java.util.Arrays;

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


    public static ReservationStatus isInStatus(String status){
        return Arrays.stream(ReservationStatus.values())
                .filter(v -> v.value.equals(status))
                .findFirst()
                .orElseThrow(PlaceReservationStatusNotMatch::new);
    }

}
