package com.camping.camping.applications;

import com.camping.camping.aws.S3UploadService;
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
import com.camping.camping.repositories.CategoryRepository;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

class AddProductServiceTest {

    private S3UploadService s3UploadService;

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ProductFirstOptionRepository productFirstOptionRepository;
    private ProductSecondOptionRepository productSecondOptionRepository;

    private ProductImageRepository productImageRepository;

    private AddProductService addProductService;

    @BeforeEach
    void setUp() {

        s3UploadService = mock(S3UploadService.class);
        categoryRepository = mock(CategoryRepository.class);
        productRepository = mock(ProductRepository.class);
        productFirstOptionRepository = mock(ProductFirstOptionRepository.class);
        productSecondOptionRepository = mock(ProductSecondOptionRepository.class);
        productImageRepository = mock(ProductImageRepository.class);

        addProductService = new AddProductService(
                s3UploadService,
                categoryRepository,
                productRepository,
                productFirstOptionRepository,
                productSecondOptionRepository,
                productImageRepository
        );

    }


    @Test
    @DisplayName("상품 옵션 생성 테스트")
    void addProduct() throws IOException {

        Category category = new Category(new Name("testcate1"));

        String name = "testProduct";
        Long price = 1000L;
        String description = "testDesc";

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

        given(categoryRepository.findById(category.id())).willReturn(Optional.of(category));

        Product product = new Product(
                category,
                new Name(name),
                new Money(price),
                new Description(description)
        );

        productRepository.save(product);

        ProductImage productImage = new ProductImage(
                product,
                url);

        productImageRepository.save(productImage);

        ProductFirstOption firstOption = new ProductFirstOption(
                product,
                new FirstOptionName("firstName"),
                new Money(100L)
        );

        productFirstOptionRepository.save(firstOption);

        ProductSecondOption secondOption = new ProductSecondOption(
                firstOption,
                new SecondOptionName("secondeName"),
                new Money(10L)
        );

        productSecondOptionRepository.save(secondOption);

        String res = addProductService.addProduct(category.id(), name, price, description, image1);

        assertThat(res).isEqualTo("success");
    }




}