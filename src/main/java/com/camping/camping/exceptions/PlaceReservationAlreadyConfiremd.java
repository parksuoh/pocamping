package com.camping.camping.exceptions;

public class PlaceReservationAlreadyConfiremd extends RuntimeException {

    public PlaceReservationAlreadyConfiremd() {
        super("그날 예약 확정이 이미 존재합니다.");
    }
}
