package com.camping.camping.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.camping.camping.CampingApplication;
import com.camping.camping.applications.AddCartService;
import com.camping.camping.applications.DeleteCartItemService;
import com.camping.camping.applications.GetCartItemsService;
import com.camping.camping.applications.UpdateCartItemService;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.dtos.CartItemsResponseDto;
import com.camping.camping.security.AccessTokenAuthenticationFilter;
import com.camping.camping.security.AccessTokenGenerator;
import com.camping.camping.security.AccessTokenService;
import com.camping.camping.security.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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