package com.camping.camping.applications;

import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.dtos.LoginResponseDto;
import com.camping.camping.exceptions.NameAlreadyExist;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.exceptions.PasswordNotMatch;
import com.camping.camping.repositories.UserRepository;
import com.camping.camping.security.AccessTokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@SpringBootTest(classes = {AccessTokenGenerator.class})
class LoginServiceTest {

    private PasswordEncoder passwordEncoder;
    private AccessTokenGenerator accessTokenGenerator;
    private UserRepository userRepository;
    private LoginService loginService;

    @Value("${jwt.secret}")
    private String secret;

    @BeforeEach
    void setUp() {

        passwordEncoder = mock(PasswordEncoder.class);

        accessTokenGenerator = new AccessTokenGenerator(secret);

        userRepository = mock(UserRepository.class);

        loginService = new LoginService(
                userRepository,
                passwordEncoder,
                accessTokenGenerator
        );
    }

    @Test
    @DisplayName("로그인 테스트")
    void loginTest() {
        String name = "user1";
        String password = "1234";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        User user = new User(name, encodedePassword, Role.ROLE_USER);

        given(userRepository.findByName(name)).willReturn(Optional.of(user));

        given(passwordEncoder.matches(password, encodedePassword)).willReturn(true);

        LoginResponseDto res = loginService.login(name, password);

        assertThat(res.token()).isNotEmpty();
    }


    @Test
    @DisplayName("로그인 테스트 - 실패 아이디없음")
    void loginTest2() {
        String name = "user10";
        String password = "1234";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        User user = new User(name, encodedePassword, Role.ROLE_USER);

        given(userRepository.findByName(name)).willReturn(Optional.empty());

        given(passwordEncoder.matches(password, encodedePassword)).willReturn(true);

        assertThrows(NameNotExist.class, () -> {
            loginService.login(name, password);
        });
    }

    @Test
    @DisplayName("로그인 테스트 - 실페 비밀번호 틀림")
    void loginTest3() {
        String name = "user1";
        String password = "12344";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        User user = new User(name, encodedePassword, Role.ROLE_USER);

        given(userRepository.findByName(name)).willReturn(Optional.of(user));

        given(passwordEncoder.matches(password, encodedePassword)).willReturn(false);

        assertThrows(PasswordNotMatch.class, () -> {
            loginService.login(name, password);
        });
    }

}