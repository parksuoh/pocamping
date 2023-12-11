package com.camping.camping.exceptions;

public class PlaceAleadyReservation extends RuntimeException {

    public PlaceAleadyReservation() {
        super("장소를 이미 예약하였습니다.");
    }
}
