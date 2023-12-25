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

class DeleteProductSecondOptionServiceTest {

    private ProductSecondOptionRepository productSecondOptionRepository;
    private CartItemRepository cartItemRepository;
    private DeleteProductSecondOptionService deleteProductSecondOptionService;

    @BeforeEach
    void setUp() {
        productSecondOptionRepository = mock(ProductSecondOptionRepository.class);
        cartItemRepository = mock(CartItemRepository.class);

        deleteProductSecondOptionService = new DeleteProductSecondOptionService(
                productSecondOptionRepository,
                cartItemRepository
        );

    }

    @Test
    @DisplayName("상품 두번째 옵션 삭제 테스트")
    void deleteProductSecondOptionTest(){
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

        ProductSecondOption secondOption = new ProductSecondOption(
                firstOption,
                new SecondOptionName("secondeName"),
                new Money(10L)
        );

        ProductSecondOption secondOption2 = new ProductSecondOption(
                firstOption,
                new SecondOptionName("secondeName2"),
                new Money(10L)
        );

        List<ProductSecondOption> secondOptions = new ArrayList<>();
        secondOptions.add(secondOption);
        secondOptions.add(secondOption2);


        given(productSecondOptionRepository
                .findById(secondOption.id()))
                .willReturn(Optional.of(secondOption));


        given(productSecondOptionRepository
                .findByProductFirstOption_Id(firstOption.id()))
                .willReturn(secondOptions);

        productSecondOptionRepository.delete(secondOption);


        String name = "user1";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        User user = new User(name, encodedePassword, Role.ROLE_USER);
        Cart cart = new Cart(user);
        CartItem cartItem = new CartItem(cart, product, firstOption, secondOption, 1);

        given(cartItemRepository
                .findByProductSecondOption_Id(secondOption.id()))
                .willReturn(List.of(cartItem));

        cartItemRepository.delete(cartItem);

        String res = deleteProductSecondOptionService.deleteProductSecondOption(secondOption.id());

        assertThat(res).isEqualTo("success");
    }


}