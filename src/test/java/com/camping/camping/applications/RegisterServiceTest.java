package com.camping.camping.applications;

import com.camping.camping.exceptions.NameAlreadyExist;
import com.camping.camping.repositories.CartRepository;
import com.camping.camping.repositories.UserRepository;
import com.camping.camping.security.AccessTokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {AccessTokenGenerator.class})
class RegisterServiceTest {

    private PasswordEncoder passwordEncoder;
    private AccessTokenGenerator accessTokenGenerator;
    private UserRepository userRepository;
    private RegisterService registerService;

    private CartRepository cartRepository;

    @Value("${jwt.secret}")
    private String secret;

    @BeforeEach
    void setUp() {



        passwordEncoder = mock(PasswordEncoder.class);

        accessTokenGenerator = new AccessTokenGenerator(secret);

        userRepository = mock(UserRepository.class);

        cartRepository = mock(CartRepository.class);

        registerService = new RegisterService(
                userRepository,
                passwordEncoder,
                accessTokenGenerator,
                cartRepository
                );
    }


    @Test
    @DisplayName("회원가입 테스트")
    void registerTest() {
        String name = "user2";
        String password = "1234";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        given(userRepository.existsByName(name)).willReturn(false);

        given(passwordEncoder.encode(password)).willReturn(encodedePassword);

        String token = registerService.register(name, password);


        assertThat(token).isNotEmpty();
    }

    @Test
    @DisplayName("회원가입 테스트 - 실패")
    void registerTest2() {
        String name = "user1";
        String password = "1234";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        given(userRepository.existsByName(name)).willReturn(true);

        given(passwordEncoder.encode(password)).willReturn(encodedePassword);


        assertThrows(NameAlreadyExist.class, () -> {
            registerService.register(name, password);
        });

    }


}