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
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import com.camping.camping.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

class AddCartServiceTest {

    private UserRepository userRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private ProductFirstOptionRepository productFirstOptionRepository;
    private ProductSecondOptionRepository productSecondOptionRepository;
    private AddCartService addCartService;

    @BeforeEach
    void setUp() {

        userRepository = mock(UserRepository.class);
        cartRepository = mock(CartRepository.class);
        cartItemRepository = mock(CartItemRepository.class);
        productRepository = mock(ProductRepository.class);
        productFirstOptionRepository = mock(ProductFirstOptionRepository.class);
        productSecondOptionRepository = mock(ProductSecondOptionRepository.class);

        addCartService = new AddCartService(
                userRepository,
                cartRepository,
                cartItemRepository,
                productRepository,
                productFirstOptionRepository,
                productSecondOptionRepository
        );

    }

    @Test
    @DisplayName("카트 추가 테스트 - 카트추가")
    void addCartTest(){
        String name = "user1";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";
        Long productId = 6L;
        Long productFirstOptionId = 8L;
        Long productSecondOptionId = 10L;
        Integer quantity = 1;


        User user = new User(name, encodedePassword, Role.ROLE_USER);

        given(userRepository.findByName(name)).willReturn(Optional.of(user));

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


        given(productRepository.findById(productId)).willReturn(Optional.of(product));
        given(productFirstOptionRepository.findById(productFirstOptionId)).willReturn(Optional.of(firstOption));
        given(productSecondOptionRepository.findById(productSecondOptionId)).willReturn(Optional.of(secondOption));

        given(cartItemRepository
                .findByCart_IdAndProduct_IdAndProductFirstOption_IdAndProductSecondOption_Id(
                        cart.id(),
                        productId,
                        productFirstOptionId,
                        productSecondOptionId
                ))
                .willReturn(null);

        String res = addCartService.addCart(name, productId, productFirstOptionId, productSecondOptionId, quantity);

        assertThat(res).isEqualTo("success");

        verify(cartItemRepository).save(any(CartItem.class));
    }



    @Test
    @DisplayName("카트 추가 테스트 - 물품추가")
    void addCartTest2(){
        String name = "user1";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";
        Long productId = 6L;
        Long productFirstOptionId = 8L;
        Long productSecondOptionId = 10L;
        Integer quantity = 1;


        User user = new User(name, encodedePassword, Role.ROLE_USER);

        given(userRepository.findByName(name)).willReturn(Optional.of(user));

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


        given(productRepository.findById(productId)).willReturn(Optional.of(product));
        given(productFirstOptionRepository.findById(productFirstOptionId)).willReturn(Optional.of(firstOption));
        given(productSecondOptionRepository.findById(productSecondOptionId)).willReturn(Optional.of(secondOption));

        CartItem cartItem = new CartItem(
                cart,
                product,
                firstOption,
                secondOption,
                quantity
        );

        given(cartItemRepository
                .findByCart_IdAndProduct_IdAndProductFirstOption_IdAndProductSecondOption_Id(
                        cart.id(),
                        productId,
                        productFirstOptionId,
                        productSecondOptionId
                ))
                .willReturn(cartItem);

        String res = addCartService.addCart(name, productId, productFirstOptionId, productSecondOptionId, quantity);

        assertThat(res).isEqualTo("success");

        assertThat(cartItem.quantity()).isEqualTo(2);

    }


}