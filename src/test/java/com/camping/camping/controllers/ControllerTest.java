package com.camping.camping.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.security.AccessTokenAuthenticationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@TestPropertySource("classpath:application.yaml")
public class ControllerTest {

    protected final String userName = "test1";
    protected final String adminName = "admin";

    protected String userAccessToken;

    protected String adminAccessToken;

    @Value("${jwt.secret}")
    private String secret = "CAMPT";

    private Algorithm algorithm;

    @MockBean
    private AccessTokenAuthenticationFilter authenticationFilter;

    @BeforeEach
    void setUp() {

        algorithm = Algorithm.HMAC256(secret);

        userAccessToken = JWT.create()
                .withClaim("name", userName)
                .withClaim("role", Role.ROLE_USER.toString())
                .withExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS))
                .sign(algorithm);

        adminAccessToken = JWT.create()
                .withClaim("name", adminName)
                .withClaim("role", Role.ROLE_ADMIN.toString())
                .withExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS))
                .sign(algorithm);

    }

    @Test
    @DisplayName("test 성공")
    void ttteessstttt() throws Exception {
        System.out.println("돼라");

    }

}