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
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class AddAdminProductSecondOptionServiceTest {

    private ProductFirstOptionRepository productFirstOptionRepository;
    private ProductSecondOptionRepository productSecondOptionRepository;
    private AddAdminProductSecondOptionService addAdminProductSecondOptionService;

    @BeforeEach
    void setUp() {
        productFirstOptionRepository = mock(ProductFirstOptionRepository.class);
        productSecondOptionRepository = mock(ProductSecondOptionRepository.class);

        addAdminProductSecondOptionService = new AddAdminProductSecondOptionService(
                productFirstOptionRepository,
                productSecondOptionRepository
        );


    }

    @Test
    @DisplayName("상품 두번째 옵션 생성 테스트")
    void addAdminProductSecondOptionTest(){

        String name = "secondeName";
        Long addPrice = 10L;

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


        given(productFirstOptionRepository
                .findById(firstOption.id()))
                .willReturn(Optional.of(firstOption));


        ProductSecondOption secondOption = new ProductSecondOption(
                firstOption,
                new SecondOptionName(name),
                new Money(addPrice)
        );

        productSecondOptionRepository.save(secondOption);

        String res = addAdminProductSecondOptionService.addAdminProductSecondOption(firstOption.id(), name, addPrice);

        assertThat(res).isEqualTo("success");

    }



}