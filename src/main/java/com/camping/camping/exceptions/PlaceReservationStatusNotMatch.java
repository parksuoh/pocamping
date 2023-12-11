package com.camping.camping.exceptions;

public class PlaceReservationStatusNotMatch extends RuntimeException {

    public PlaceReservationStatusNotMatch() {
        super("예약상태가 존재하지 않습니다.");
    }
}
