package com.camping.camping.dtos;

import java.util.List;


public record GetPlacesDto(
         Long placeId,

         String name,

         Long price,

         List<PlaceImageDto> placeImages
){}

