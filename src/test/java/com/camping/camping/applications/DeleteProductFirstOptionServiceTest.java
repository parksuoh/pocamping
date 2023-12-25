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
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteProductFirstOptionServiceTest {

    private ProductFirstOptionRepository productFirstOptionRepository;
    private CartItemRepository cartItemRepository;
    private ProductSecondOptionRepository productSecondOptionRepository;
    private DeleteProductFirstOptionService deleteProductFirstOptionService;

    @BeforeEach
    void setUp() {

        productFirstOptionRepository = mock(ProductFirstOptionRepository.class);
        cartItemRepository = mock(CartItemRepository.class);
        productSecondOptionRepository = mock(ProductSecondOptionRepository.class);

        deleteProductFirstOptionService = new DeleteProductFirstOptionService(
                productFirstOptionRepository,
                cartItemRepository,
                productSecondOptionRepository
        );

    }

    @Test
    @DisplayName("상품 첫번째 옵션 삭제 테스트")
    void deleteProductFirstOptionTest() {

        Category category = new Category(new Name("testcate1"));

        Product product = new Product(
                category,
                new Name("testProduct"),
                new Money(1000L),
                new Description("testDesc")
        );

        ProductFirstOption firstOption = new ProductFirstOption(
                product,
                new FirstOptionName("firstName"),
                new Money(100L)
        );

        ProductFirstOption firstOption2 = new ProductFirstOption(
                product,
                new FirstOptionName("firstName2"),
                new Money(200L)
        );

        ProductSecondOption secondOption = new ProductSecondOption(
                firstOption,
                new SecondOptionName("secondeName"),
                new Money(10L)
        );

        given(productFirstOptionRepository
                .findById(firstOption.id()))
                .willReturn(Optional.of(firstOption));


        List<ProductFirstOption> firstOptions = new ArrayList<>();
        firstOptions.add(firstOption);
        firstOptions.add(firstOption2);
        given(productFirstOptionRepository
                .findByProduct_Id(product.id()))
                .willReturn(firstOptions);

        productFirstOptionRepository.delete(firstOption);

        given(productSecondOptionRepository
                .findByProductFirstOption_Id(secondOption.id()))
                .willReturn(List.of(secondOption));

        productSecondOptionRepository.delete(secondOption);

        String name = "user1";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        User user = new User(name, encodedePassword, Role.ROLE_USER);
        Cart cart = new Cart(user);
        CartItem cartItem = new CartItem(cart, product, firstOption, secondOption, 1);

        given(cartItemRepository
                .findByProductFirstOption_Id(firstOption.id()))
                .willReturn(List.of(cartItem));

        cartItemRepository.delete(cartItem);

        String res = deleteProductFirstOptionService.deleteProductFirstOption(firstOption.id());

        assertThat(res).isEqualTo("success");

    }



}