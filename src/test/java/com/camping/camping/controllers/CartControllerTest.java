package com.camping.camping.controllers;


import com.camping.camping.applications.AddCartService;
import com.camping.camping.applications.DeleteCartItemService;
import com.camping.camping.applications.GetCartItemsService;
import com.camping.camping.applications.UpdateCartItemService;
import com.camping.camping.dtos.CartItemsResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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



//    @Test
//    @DisplayName("GET /api/cart 카트목록 성공")
//    @WithMockUser(userName)
//    void getCartSuccess() throws Exception {
//
//        CartItemsResponseDto responseDto = new CartItemsResponseDto(
//                10L,
//                2,
//                1L,
//                "test1",
//                1000L,
//                1L,
//                "test1-1",
//                100L,
//                1L,
//                "test1-1-1",
//                10L,
//                1110L,
//                2220L
//        );
//
//
//        given(getCartItemsService.getCartItems(userName))
//                .willReturn(List.of(responseDto));
//
//        mockMvc.perform(get("/api/cart")
//                        .header("Authorization", "Bearer " + userAccessToken)
//                )
//                .andExpect(status().isOk());
//    }



}