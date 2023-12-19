package com.camping.camping.repositories.daos;

import com.camping.camping.dtos.GetCartItemByCartItemIdDto;
import com.camping.camping.dtos.GetCartItemsDto;

import java.util.List;

public interface JdbcCartItemRepository {

    List<GetCartItemsDto> findByCartId(Long cartId);

    GetCartItemByCartItemIdDto findByCartItemId(Long cartItemId);

}
