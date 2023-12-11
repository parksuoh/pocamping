package com.camping.camping.exceptions;

public class PlaceReservationNotExist extends RuntimeException {

    public PlaceReservationNotExist() {
        super("예약이 존재하지 않습니다.");
    }
}
