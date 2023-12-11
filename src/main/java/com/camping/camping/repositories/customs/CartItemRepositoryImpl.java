package com.camping.camping.repositories.customs;

import com.camping.camping.dtos.GetCartItemByCartItemIdDto;
import com.camping.camping.dtos.GetCartItemsDto;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

public class CartItemRepositoryImpl implements CartItemRepositoryCustom {

    private final JdbcClient jdbcClient;
    public CartItemRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<GetCartItemsDto> findByCartId(Long cartId) {

        String query = """
                 SELECT ci.id AS cart_item_id, 
                       ci.quantity, 
                       p.id AS product_id, 
                       p.name, 
                       p.price,
                       pf.id AS product_first_option_id, 
                       pf.first_name AS product_first_option_name, 
                       pf.add_price AS product_first_add_price,
                       ps.id AS product_second_option_id, 
                       ps.second_name AS product_second_option_name, 
                       ps.add_price AS product_second_add_price 
                  FROM (SELECT * FROM cart_item WHERE cart_id=:cid ) ci 
                 INNER JOIN product p 
                    ON ci.product_id = p.id 
                 INNER JOIN product_first_option pf 
                    ON p.id = pf.product_id 
                 INNER JOIN product_second_option ps 
                    ON ps.product_first_option_id = pf.id;
                """;

        return jdbcClient.sql(query)
                .param("cid", cartId)
                .query(GetCartItemsDto.class)
                .list();

    }

    @Override
    public GetCartItemByCartItemIdDto findByCartItemId(Long cartItemId) {

        String query = """
                SELECT p.id AS product_id,
                       p.name AS product_name,
                       p.price AS product_price,
                       pf.id AS product_first_option_id,
                       pf.first_name AS product_first_option_name,
                       pf.add_price AS product_first_option_price,
                       ps.id AS product_second_option_id,
                       ps.second_name AS product_second_option_name,
                       ps.add_price AS product_second_option_price,
                       ci.quantity
                  FROM (SELECT * FROM cart_item WHERE id=:id) ci 
                 INNER JOIN product p 
                    ON ci.product_id = p.id 
                 INNER JOIN product_first_option pf 
                    ON p.id = pf.product_id 
                   AND ci.product_first_option_id = pf.id 
                 INNER JOIN product_second_option ps 
                    ON ps.product_first_option_id = pf.id 
                   AND ci.product_second_option_id = ps.id;
                """;

        return jdbcClient.sql(query)
                .param("id", cartItemId)
                .query(GetCartItemByCartItemIdDto.class)
                .single();


    }
}
