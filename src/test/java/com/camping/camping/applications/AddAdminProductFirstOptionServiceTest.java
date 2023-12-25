package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class AddAdminProductFirstOptionServiceTest {

    private ProductRepository productRepository;
    private ProductFirstOptionRepository productFirstOptionRepository;
    private ProductSecondOptionRepository productSecondOptionRepository;

    private AddAdminProductFirstOptionService addAdminProductFirstOptionService;

    @BeforeEach
    void setUp() {

        productRepository = mock(ProductRepository.class);
        productFirstOptionRepository = mock(ProductFirstOptionRepository.class);
        productSecondOptionRepository = mock(ProductSecondOptionRepository.class);

        addAdminProductFirstOptionService = new AddAdminProductFirstOptionService(
                productRepository,
                productFirstOptionRepository,
                productSecondOptionRepository
        );

    }



    @Test
    @DisplayName("상품 첫번째 옵션 생성 테스트")
    void addAdminProductFirstOptionTest() {

        String name = "firstName";
        Long addPrice = 100L;

        Category category = new Category(new Name("testcate1"));

        Product product = new Product(
                category,
                new Name("testProduct"),
                new Money(1000L),
                new Description("testDesc")
        );

        given(productRepository
                .findById(product.id()))
                .willReturn(Optional.of(product));


        ProductFirstOption firstOption = new ProductFirstOption(
                product,
                new FirstOptionName(name),
                new Money(addPrice)
        );

        productFirstOptionRepository.save(firstOption);

        ProductSecondOption secondOption = new ProductSecondOption(
                firstOption,
                new SecondOptionName("secondeName"),
                new Money(10L)
        );

        productSecondOptionRepository.save(secondOption);

        String res = addAdminProductFirstOptionService.addAdminProductFirstOption(product.id(), name, addPrice);

        assertThat(res).isEqualTo("success");

    }



}