package com.camping.camping.repositories.customs;

import com.camping.camping.dtos.GetCartItemByCartItemIdDto;
import com.camping.camping.dtos.GetCartItemsDto;

import java.util.List;

public interface CartItemRepositoryCustom {

    List<GetCartItemsDto> findByCartId(Long cartId);

    GetCartItemByCartItemIdDto findByCartItemId(Long cartItemId);

}
