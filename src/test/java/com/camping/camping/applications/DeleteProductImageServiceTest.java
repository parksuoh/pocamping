package com.camping.camping.applications;

import com.camping.camping.aws.S3DeleteService;
import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductImage;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.repositories.ProductImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteProductImageServiceTest {

    private S3DeleteService s3DeleteService;
    private ProductImageRepository productImageRepository;
    private DeleteProductImageService deleteProductImageService;

    @BeforeEach
    void setUp() {

        s3DeleteService = mock(S3DeleteService.class);
        productImageRepository = mock(ProductImageRepository.class);

        deleteProductImageService = new DeleteProductImageService(
                s3DeleteService,
                productImageRepository
        );

    }


    @Test
    @DisplayName("상품이미지 삭제 테스트")
    void deleteProductImageTest() throws IOException {

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


        given(productImageRepository
                .findById(product.id()))
                .willReturn(Optional.of(productImage));

        s3DeleteService.deleteFile(productImage.url());

        productImageRepository.delete(productImage);

        String res = deleteProductImageService.deleteProductImage(productImage.id());

        assertThat(res).isEqualTo("success");
    }





}