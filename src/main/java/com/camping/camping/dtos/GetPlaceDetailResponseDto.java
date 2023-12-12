package com.camping.camping.dtos;

import java.util.List;

public record GetPlaceDetailResponseDto(
         Long placeId,

         String name,

         Long price,

         String description,

         List<PlaceImageDto> placeImages
){}


