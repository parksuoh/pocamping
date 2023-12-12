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

