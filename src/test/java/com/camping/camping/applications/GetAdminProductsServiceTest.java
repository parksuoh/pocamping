package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductImage;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetAdminProductsServiceTest {

    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;
    private GetAdminProductsService getAdminProductsService;

    @BeforeEach
    void setUp(){

        productRepository = mock(ProductRepository.class);
        productImageRepository = mock(ProductImageRepository.class);

        getAdminProductsService= new GetAdminProductsService(
                productRepository,
                productImageRepository
        );

    }

    @Test
    @DisplayName("관리자 상품 리스트 가져오기 테스트")
    void getAdminProductsTest(){
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

        given(productRepository
                .findAll(Sort.by(Sort.Direction.DESC, "id")))
                .willReturn(List.of(product));

        given(productImageRepository
                .findByProduct_Id(product.id()))
                .willReturn(List.of(productImage));

        List<GetProductByCategoryDto> adminProducts = getAdminProductsService.getAdminProducts();

        assertThat(adminProducts).hasSize(1);

    }



}