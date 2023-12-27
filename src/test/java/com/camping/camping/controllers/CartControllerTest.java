package com.camping.camping.controllers;


import com.camping.camping.applications.AddCartService;
import com.camping.camping.applications.DeleteCartItemService;
import com.camping.camping.applications.GetCartItemsService;
import com.camping.camping.applications.UpdateCartItemService;
import com.camping.camping.dtos.AddCartRequestDto;
import com.camping.camping.dtos.CartItemsResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@WebMvcTest(CartController.class)
class CartControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddCartService addCartService;
    @MockBean
    private GetCartItemsService getCartItemsService;
    @MockBean
    private DeleteCartItemService deleteCartItemService;
    @MockBean
    private UpdateCartItemService updateCartItemService;



    @Test
    @DisplayName("GET /api/cart 카트목록 ")
    @WithMockUser(userName)
    void getCartTest() throws Exception {

        CartItemsResponseDto responseDto = new CartItemsResponseDto(
                10L,
                2,
                1L,
                "test1",
                1000L,
                1L,
                "test1-1",
                100L,
                1L,
                "test1-1-1",
                10L,
                1110L,
                2220L
        );


        when(getCartItemsService.getCartItems(any()))
                .thenReturn(List.of(responseDto));

        mockMvc.perform(get("/api/cart")
                        .header("Authorization", "Bearer " + userAccessToken)
                )
//                .andExpect(status().isOk())
                .andDo(print());;
    }


    @Test
    @DisplayName("POST /api/cart 카트추가 ")
    @WithMockUser(userName)
    void addCartTest() throws Exception {

        String name = "test1";

        AddCartRequestDto addCartRequestDto = new AddCartRequestDto(
                1L,
                1L,
                1L,
                1
        );

        String json = String.format(
                """
                        {
                            "productId": %d,
                            "productFirstOptionId": %d,
                            "productSecondOptionId": %d,
                            "quantity": %d
                        }
                        """,
                addCartRequestDto.productId(),
                addCartRequestDto.productFirstOptionId(),
                addCartRequestDto.productSecondOptionId(),
                addCartRequestDto.quantity()
        );

        mockMvc.perform(post("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json).with(csrf())
                        .header("Authorization", "Bearer " + userAccessToken)
                )
                .andExpect(status().isCreated());


        verify(addCartService)
                .addCart(
                eq(name),
                eq(addCartRequestDto.productId()),
                eq(addCartRequestDto.productFirstOptionId()),
                eq(addCartRequestDto.productSecondOptionId()),
                eq(addCartRequestDto.quantity())
        );


    }

}