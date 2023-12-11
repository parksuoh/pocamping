package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record UpdatePlaceReservationDto(
        @Positive
         Long placeReservationId,

        @NotBlank
         String reservcationStatus
) {}


//public class UpdatePlaceReservationDto {
//
//    @Positive
//    private Long placeReservationId;
//
//    @NotBlank
//    private String reservcationStatus;
//
//    public UpdatePlaceReservationDto(Long placeReservationId, String reservcationStatus) {
//        this.placeReservationId = placeReservationId;
//        this.reservcationStatus = reservcationStatus;
//    }
//
//    public Long getPlaceReservationId() {
//        return placeReservationId;
//    }
//
//    public void setPlaceReservationId(Long placeReservationId) {
//        this.placeReservationId = placeReservationId;
//    }
//
//    public String getReservcationStatus() {
//        return reservcationStatus;
//    }
//
//    public void setReservcationStatus(String reservcationStatus) {
//        this.reservcationStatus = reservcationStatus;
//    }
//}
