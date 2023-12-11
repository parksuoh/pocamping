package com.camping.camping.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;


public record AddPlaceReservationRequestDto(
        @Positive
         Long placeId,
        @FutureOrPresent
         LocalDate reservationDate
) {}


//public class AddPlaceReservationRequestDto {
//
//    @Positive
//    private Long placeId;
//
//    @FutureOrPresent
//    private LocalDate reservationDate;
//
//    public AddPlaceReservationRequestDto(Long placeId, LocalDate reservationDate) {
//        this.placeId = placeId;
//        this.reservationDate = reservationDate;
//    }
//
//    public Long getPlaceId() {
//        return placeId;
//    }
//
//    public void setPlaceId(Long placeId) {
//        this.placeId = placeId;
//    }
//
//    public LocalDate getReservationDate() {
//        return reservationDate;
//    }
//
//    public void setReservationDate(LocalDate reservationDate) {
//        this.reservationDate = reservationDate;
//    }
//}
