package com.camping.camping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record UpdatePlaceReservationDto(
        @Positive
         Long placeReservationId,

        @NotBlank
         String reservcationStatus
) {}


