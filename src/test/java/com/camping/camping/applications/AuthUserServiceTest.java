package com.camping.camping.applications;

import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.dtos.AuthInfoDto;
import com.camping.camping.dtos.AuthUserDto;
import com.camping.camping.repositories.UserRepository;
import com.camping.camping.security.AccessTokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AccessTokenGenerator.class})
class AuthUserServiceTest {
    private AccessTokenGenerator accessTokenGenerator;
    private UserRepository userRepository;

    private AuthUserService authUserService;

    @Value("${jwt.secret}")
    private String secret;

    @BeforeEach
    void setUp() {


        accessTokenGenerator = new AccessTokenGenerator(secret);

        userRepository = mock(UserRepository.class);


        authUserService = new AuthUserService(
                accessTokenGenerator,
                userRepository
        );
    }

    @Test
    @DisplayName("인증 테스트")
    void authTest() {

        String name = "user1";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";


        String token = accessTokenGenerator.generate(name, Role.ROLE_USER);


        boolean verify = accessTokenGenerator.verify(token);

        AuthInfoDto tokenInfo = accessTokenGenerator.getTokenInfo(token);

        User user = new User(name, encodedePassword, Role.ROLE_USER);

        given(userRepository.findByName(tokenInfo.name())).willReturn(Optional.of(user));

        String generate = accessTokenGenerator.generate(user.name(), user.role());

        AuthUserDto res = authUserService.auth(token);

        assertThat(res.accessToken()).isNotEmpty();

    }





}