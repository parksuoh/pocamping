package com.camping.camping.dtos;

import java.util.List;


public record GetProductDetailDto(
         Long id,
         String name,
         Long price,
         String description,
         List<ProductImageDto> images,
         List<GetProductFitstOptionDto> firstOptions
) {}


