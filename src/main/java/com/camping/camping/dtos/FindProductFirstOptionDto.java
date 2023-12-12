package com.camping.camping.dtos;

import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;

public record FindProductFirstOptionDto(
         Long id,
         FirstOptionName name,
         Money addPrice
) {}
