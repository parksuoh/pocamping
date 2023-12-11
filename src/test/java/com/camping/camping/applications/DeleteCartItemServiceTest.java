package com.camping.camping.applications;

import com.camping.camping.domains.Cart;
import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.CartRepository;
import com.camping.camping.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteCartItemServiceTest {

    private UserRepository userRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private DeleteCartItemService deleteCartItemService;


    @BeforeEach
    void setUp() {

        userRepository = mock(UserRepository.class);
        cartRepository = mock(CartRepository.class);
        cartItemRepository = mock(CartItemRepository.class);

        deleteCartItemService = new DeleteCartItemService(
                userRepository,
                cartRepository,
                cartItemRepository
        );

    }


    @Test
    @DisplayName("카트 삭제 테스트")
    void upadateCartTest() {
        String name = "user1";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        User user = new User(name, encodedePassword, Role.ROLE_USER);

        given(userRepository.findByName(name))
                .willReturn(Optional.of(user));

        Cart cart = new Cart(user);

        given(cartRepository.findTop1ByUser_Id(user.id())).willReturn(cart);


        Category category = new Category(new Name("testCate"));

        Product product = new Product(
                category,
                new Name("testProduct"),
                new Money(2000L),
                new Description("testDiscr")
        );

        ProductFirstOption firstOption = new ProductFirstOption(
                product,
                new FirstOptionName("testFirst"),
                new Money(100L)
        );

        ProductSecondOption secondOption = new ProductSecondOption(
                firstOption,
                new SecondOptionName("second"),
                new Money(10L)
        );

        CartItem cartItem = new CartItem(
                cart,
                product,
                firstOption,
                secondOption,
                1
        );


        given(cartItemRepository.findById(cartItem.id()))
                .willReturn(Optional.of(cartItem));


        String res = deleteCartItemService.deleteCartItem(name, cartItem.id());

        assertThat(res).isEqualTo("success");
        assertThat(cartItem.id()).isNull();
    }


}