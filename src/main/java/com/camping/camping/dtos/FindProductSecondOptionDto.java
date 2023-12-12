package com.camping.camping.dtos;

import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.SecondOptionName;


public record FindProductSecondOptionDto(
         Long id,
         SecondOptionName name,
         Money addPrice
) {}


