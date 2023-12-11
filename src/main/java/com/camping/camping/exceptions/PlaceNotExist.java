package com.camping.camping.exceptions;

public class PlaceNotExist extends RuntimeException {

    public PlaceNotExist() {
        super("장소가 존재하지 않습니다.");
    }
}
