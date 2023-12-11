package com.camping.camping.applications;

import com.camping.camping.domains.Cart;
import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.dtos.CartItemsResponseDto;
import com.camping.camping.dtos.GetCartItemsDto;
import com.camping.camping.exceptions.NameAlreadyExist;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.CartRepository;
import com.camping.camping.repositories.UserRepository;
import com.camping.camping.security.AccessTokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCartItemsServiceTest {

    private UserRepository userRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private GetCartItemsService getCartItemsService;


    @BeforeEach
    void setUp() {

        userRepository = mock(UserRepository.class);
        cartRepository = mock(CartRepository.class);
        cartItemRepository = mock(CartItemRepository.class);

        getCartItemsService = new GetCartItemsService(
                userRepository,
                cartRepository,
                cartItemRepository
        );
    }


    @Test
    @DisplayName("카트목록 가져오기 테스트")
    void getCartItemTest() {
//        String name = "user1";
//        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";
//
//        User user = new User(name, encodedePassword, Role.ROLE_USER);
//
//        given(userRepository.findByName(name)).willReturn(Optional.of(user));
//
//        Cart cart = new Cart(user);
//
//        given(cartRepository.findTop1ByUser_Id(user.id())).willReturn(cart);
//
//        GetCartItemsDto getCartItemsDto = new GetCartItemsDto(
//                8L,
//                2,
//                1L,
//                new Name("test1"),
//                new Money(2000L),
//1L,
//                new FirstOptionName("test1-1"),
//                new Money(500L),
//1L,
//                new SecondOptionName("test1-1-1"),
//                new Money(30L)
//        );
//
//
//        given(cartItemRepository.findByCartId(cart.id())).willReturn(List.of(getCartItemsDto));
//
//        List<CartItemsResponseDto> res = getCartItemsService.getCartItems(name);
//
//        assertThat(res).asList();

    }


    @Test
    @DisplayName("카트목록 가져오기 테스트 - 실패")
    void getCartItemTest2() {
        String name = "user1";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        User user = new User(name, encodedePassword, Role.ROLE_USER);

        given(userRepository.findByName(name)).willReturn(Optional.empty());


        assertThrows(NameNotExist.class, () -> {
            getCartItemsService.getCartItems(name);
        });
    }


}