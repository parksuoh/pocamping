package com.camping.camping.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.camping.camping.CampingApplication;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.dtos.AuthUserDto;
import com.camping.camping.security.AccessTokenAuthenticationFilter;
import com.camping.camping.security.AccessTokenGenerator;
import com.camping.camping.security.AccessTokenService;
import com.camping.camping.security.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@TestPropertySource("classpath:application.yaml")
public class ControllerTest {

    protected final String userName = "test1";
    protected final String adminName = "admin";

    protected String userAccessToken;

    protected String adminAccessToken;


    private final String secret = "testSecret";

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

}