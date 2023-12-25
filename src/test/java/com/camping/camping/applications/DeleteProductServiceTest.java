package com.camping.camping.applications;

import com.camping.camping.aws.S3DeleteService;
import com.camping.camping.domains.Cart;
import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductImage;
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
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteProductServiceTest {

    private ProductRepository productRepository;
    private ProductFirstOptionRepository productFirstOptionRepository;
    private ProductSecondOptionRepository productSecondOptionRepository;
    private ProductImageRepository productImageRepository;
    private S3DeleteService s3DeleteService;
    private CartItemRepository cartItemRepository;
    private DeleteProductService deleteProductService;


    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productFirstOptionRepository = mock(ProductFirstOptionRepository.class);
        productSecondOptionRepository = mock(ProductSecondOptionRepository.class);
        productImageRepository = mock(ProductImageRepository.class);
        s3DeleteService = mock(S3DeleteService.class);
        cartItemRepository = mock(CartItemRepository.class);

        deleteProductService = new DeleteProductService(
                productRepository,
                productFirstOptionRepository,
                productSecondOptionRepository,
                productImageRepository,
                s3DeleteService,
                cartItemRepository
        );

    }


    void deleteProductTest() throws IOException {
        Category category = new Category(new Name("testcate1"));

        Product product = new Product(
                category,
                new Name("testProduct"),
                new Money(1000L),
                new Description("testDesc")
        );

        ProductImage productImage = new ProductImage(
                product,
                "testUrl"
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



        given(productRepository
                .findById(product.id()))
                .willReturn(Optional.of(product));

        given(productFirstOptionRepository
                .findByProduct_Id(product.id()))
                .willReturn(List.of(firstOption));

        given(productImageRepository
                .findByProduct_Id(product.id()))
                .willReturn(List.of(productImage));




        String name = "user1";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        User user = new User(name, encodedePassword, Role.ROLE_USER);
        Cart cart = new Cart(user);
        CartItem cartItem = new CartItem(cart, product, firstOption, secondOption, 1);

        given(cartItemRepository
                .findByProduct_Id(product.id()))
                .willReturn(List.of(cartItem));

        given(productSecondOptionRepository
                .findByProductFirstOption_Id(firstOption.id()))
                .willReturn(List.of(secondOption));

        productFirstOptionRepository.delete(firstOption);

        productSecondOptionRepository.delete(secondOption);

        s3DeleteService.deleteFile(productImage.url());

        productImageRepository.delete(productImage);

        cartItemRepository.delete(cartItem);

        productRepository.delete(product);

        String res = deleteProductService.deleteProduct(product.id());

        assertThat(res).isEqualTo("success");
    }



}