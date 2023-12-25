package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductImage;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.dtos.GetProductDetailDto;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetAdminProductDetailServiceTest {


    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;

    private ProductFirstOptionRepository productFirstOptionRepository;
    private ProductSecondOptionRepository productSecondOptionRepository;
    private GetAdminProductDetailService getAdminProductDetailService;

    @BeforeEach
    void setUp() {

        productRepository = mock(ProductRepository.class);
        productImageRepository = mock(ProductImageRepository.class);
        productFirstOptionRepository = mock(ProductFirstOptionRepository.class);
        productSecondOptionRepository = mock(ProductSecondOptionRepository.class);


        getAdminProductDetailService = new GetAdminProductDetailService(
                productRepository,
                productImageRepository,
                productFirstOptionRepository,
                productSecondOptionRepository
        );

    }

    @Test
    @DisplayName("관리자 상품 상세 가져오기 테스트")
    void getProductDetailTest(){

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

        given(productImageRepository
                .findByProduct_Id(product.id()))
                .willReturn(List.of(productImage));

        given(productFirstOptionRepository
                .findByProduct_Id(product.id()))
                .willReturn(List.of(firstOption));

        given(productSecondOptionRepository
                .findByProductFirstOption_Id(firstOption.id()))
                .willReturn(List.of(secondOption));

        GetProductDetailDto productDetail = getAdminProductDetailService.getProductDetail(product.id());

        assertThat(productDetail.name()).isEqualTo("testProduct");

    }



}