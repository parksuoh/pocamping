package com.camping.camping.controllers;

import com.camping.camping.applications.AuthUserService;
import com.camping.camping.applications.LoginService;
import com.camping.camping.applications.RegisterService;
import com.camping.camping.dtos.AuthUserDto;
import com.camping.camping.dtos.LoginResponseDto;
import com.camping.camping.security.AccessTokenAuthenticationFilter;
import com.camping.camping.security.AccessTokenGenerator;
import com.camping.camping.security.AccessTokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;


@WebMvcTest(controllers = UserController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AccessTokenGenerator.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AccessTokenAuthenticationFilter.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AccessTokenService.class),
        }
)
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterService registerService;

    @MockBean
    private LoginService loginService;

    @MockBean
    private AuthUserService authUserService;





    @Test
    @DisplayName("POST /api/user/login 로그인 성공")
    @WithMockUser
    void loginSuccess() throws Exception {
        String name = "user1";
        String password = "1234";

        String json = String.format(
                """
                        {
                            "name": "%s",
                            "password": "%s"
                        }
                        """,
                name, password
        );

        LoginResponseDto loginResponseDto = new LoginResponseDto(name, "ROLE_USER", "token123");

        given(loginService.login(name, password))
                .willReturn(loginResponseDto);

        mockMvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("token123")));

    }


    @Test
    @DisplayName("POST /api/user/auth 인증 성공")
    @WithMockUser
    void authSuccess() throws Exception {
//        String name = "user1";
//        String role = "ROLE_USER";
//        String token = "token1234";
//
//        String json = String.format(
//                """
//                        {
//                            "token": "%s"
//                        }
//                        """,
//                token
//        );
//
//        AuthUserDto authUserDto = new AuthUserDto(name, role, token);
//
//        given(authUserService.auth(token))
//                .willReturn(authUserDto);
//
//        mockMvc.perform(post("/api/user/auth")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json).with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("token1234")));

    }


    @Test
    @DisplayName("POST /api/user/register 회원가입 성공")
    @WithMockUser
    void registerSuccess() throws Exception {
//        String name = "newuser";
//        String password = "1234";
//
//        String json = String.format(
//                """
//                        {
//                            "name": "%s",
//                            "password": "%s"
//                        }
//                        """,
//                name, password
//        );
//
//        given(registerService.register(name, password))
//                .willReturn("token123");
//
//        mockMvc.perform(post("/api/user/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json).with(csrf()))
//                .andExpect(status().isCreated());

    }

}