package com.camping.camping.applications;

import com.camping.camping.aws.S3UploadService;
import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductImage;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class AddProductImageServiceTest {

    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;
    private S3UploadService s3UploadService;
    private AddProductImageService addProductImageService;

    @BeforeEach
    void setUp() {

        productRepository = mock(ProductRepository.class);
        productImageRepository = mock(ProductImageRepository.class);
        s3UploadService = mock(S3UploadService.class);

        addProductImageService = new AddProductImageService(
                productRepository,
                productImageRepository,
                s3UploadService
        );

    }


    @Test
    @DisplayName("이미지 생성 테스트")
    void addProductImageTest() throws IOException {

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


        String fileName = "testcamping"; //파일명
        String contentType = "jpg"; //파일타입
        String filePath = "src/test/images/"+fileName+"."+contentType; //파일경로
        FileInputStream fileInputStream = new FileInputStream(filePath);

        MockMultipartFile image1 = new MockMultipartFile(
                fileName, //name
                fileName + "." + contentType, //originalFilename
                contentType,
                fileInputStream
        );

        String url = "testUrl";

        given(s3UploadService.saveFile(image1)).willReturn(url);

        ProductImage productImage = new ProductImage(
                product,
                url);

        productImageRepository.save(productImage);


        String res = addProductImageService.addProductImageService(product.id(), image1);

        assertThat(res).isEqualTo("success");
    }



}