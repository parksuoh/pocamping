package com.camping.camping.controllers;

import com.camping.camping.CampingApplication;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.dtos.AuthUserDto;
import com.camping.camping.security.AccessTokenGenerator;
import com.camping.camping.security.AccessTokenService;
import com.camping.camping.security.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {
        CampingApplication.class,
        WebSecurityConfig.class,
})
public class ControllerTest {

    protected final String userName = "test1";
    protected final String adminName = "admin";

    protected String userAccessToken;

    protected String adminAccessToken;

    @SpyBean
    private AccessTokenService accessTokenService;

    @SpyBean
    protected AccessTokenGenerator accessTokenGenerator;

    @BeforeEach
    void setUp() {

        userAccessToken = accessTokenGenerator.generate(userName, Role.ROLE_USER);
        adminAccessToken = accessTokenGenerator.generate(adminName, Role.ROLE_ADMIN);

        AuthUserDto user = new AuthUserDto(userName, "ROLE_USER", userAccessToken);

        given(accessTokenService.getAuthUser(userName, userAccessToken))
                .willReturn(Optional.of(user));

        AuthUserDto admin = new AuthUserDto(adminName, "ROLE_ADMIN", adminAccessToken);

        given(accessTokenService.getAuthUser(adminName, adminAccessToken))
                .willReturn(Optional.of(admin));

    }

}
