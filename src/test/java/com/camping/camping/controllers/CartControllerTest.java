package com.camping.camping.controllers;

import com.camping.camping.CampingApplication;
import com.camping.camping.applications.AddCartService;
import com.camping.camping.applications.DeleteCartItemService;
import com.camping.camping.applications.GetCartItemsService;
import com.camping.camping.applications.UpdateCartItemService;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.dtos.AuthInfoDto;
import com.camping.camping.dtos.AuthUserDto;
import com.camping.camping.dtos.CartItemsResponseDto;
import com.camping.camping.security.AccessTokenAuthenticationFilter;
import com.camping.camping.security.AccessTokenGenerator;
import com.camping.camping.security.AccessTokenService;
import com.camping.camping.security.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;



@WebMvcTest(controllers = CartController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AccessTokenGenerator.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AccessTokenAuthenticationFilter.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AccessTokenService.class),
        }
)
class CartControllerTest {

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
    @DisplayName("GET /api/cart 카트목록 성공")
    @WithMockUser
    void getCartSuccess() throws Exception {
        String name = "user1";

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


        given(getCartItemsService.getCartItems(name))
                .willReturn(List.of(responseDto));

//        mockMvc.perform(get("/api/cart")
//                        .header("Authorization", "Bearer " + userAccessToken))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("test1-1-1")));

//                mockMvc.perform(get("/api/cart")
//                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoidXNlcjEiLCJyb2xlIjowLCJleHAiOjE3MDE4MDYwMzR9.0ohNNzJzQuE9317iqZHvlcq6q4Dye-K4o0MKPBQ0fgE"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("test1-1-1")));
    }



}