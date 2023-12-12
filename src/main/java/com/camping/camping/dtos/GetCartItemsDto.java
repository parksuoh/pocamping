package com.camping.camping.dtos;


import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.SecondOptionName;

public record GetCartItemsDto(
         Long cartItemId,
         Integer quantity,
         Long productId,
         Name name,
         Long price,
         Long productFirstOptionId,
         FirstOptionName productFirstOptionName,
         Long productFirstAddPrice,
         Long productSecondOptionId,
         SecondOptionName productSecondOptionName,
         Long productSecondAddPrice
){}

