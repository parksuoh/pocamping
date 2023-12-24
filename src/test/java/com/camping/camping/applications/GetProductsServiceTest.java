package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.dtos.GetProductByCategoryDto;
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

class GetProductsServiceTest {

    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;

    private GetProductsService getProductsService;

    @BeforeEach
    void setUp(){

        productRepository = mock(ProductRepository.class);
        productImageRepository = mock(ProductImageRepository.class);

        getProductsService = new GetProductsService(
                productRepository,
                productImageRepository
        );
    }

    @Test
    @DisplayName("상품 리스트 가져오기 테스트")
    void getProductsTest() {

        Category category = new Category(new Name("testcate1"));

        Product product = new Product(
                category,
                new Name("testProduct"),
                new Money(1000L),
                new Description("testDesc")
        );

        given(productRepository.findAll(Sort.by(Sort.Direction.DESC, "id")))
                .willReturn(List.of(product));

        List<GetProductByCategoryDto> products = getProductsService.getProducts(category.id());

        assertThat(products).hasSize(1);

    }



}