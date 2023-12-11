package com.camping.camping.dtos;

import java.time.LocalDate;


public record GetPlaceReservationResponseDto(
         Long placeReservationId,
         Long placeId,
         String placeName,
         Long placePrice,
         LocalDate reservationDate,
         String reservationStatus
) {}


//public class GetPlaceReservationResponseDto {
//
//    private Long placeReservationId;
//    private Long placeId;
//    private String placeName;
//    private Long placePrice;
//    private LocalDate reservationDate;
//    private String reservationStatus;
//
//    public GetPlaceReservationResponseDto(Long placeReservationId, Long placeId, String placeName, Long placePrice, LocalDate reservationDate, String reservationStatus) {
//        this.placeReservationId = placeReservationId;
//        this.placeId = placeId;
//        this.placeName = placeName;
//        this.placePrice = placePrice;
//        this.reservationDate = reservationDate;
//        this.reservationStatus = reservationStatus;
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
//    public Long getPlaceId() {
//        return placeId;
//    }
//
//    public void setPlaceId(Long placeId) {
//        this.placeId = placeId;
//    }
//
//    public String getPlaceName() {
//        return placeName;
//    }
//
//    public void setPlaceName(String placeName) {
//        this.placeName = placeName;
//    }
//
//    public Long getPlacePrice() {
//        return placePrice;
//    }
//
//    public void setPlacePrice(Long placePrice) {
//        this.placePrice = placePrice;
//    }
//
//    public LocalDate getReservationDate() {
//        return reservationDate;
//    }
//
//    public void setReservationDate(LocalDate reservationDate) {
//        this.reservationDate = reservationDate;
//    }
//
//    public String getReservationStatus() {
//        return reservationStatus;
//    }
//
//    public void setReservationStatus(String reservationStatus) {
//        this.reservationStatus = reservationStatus;
//    }
//}
