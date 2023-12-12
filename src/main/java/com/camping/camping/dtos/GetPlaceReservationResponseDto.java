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

