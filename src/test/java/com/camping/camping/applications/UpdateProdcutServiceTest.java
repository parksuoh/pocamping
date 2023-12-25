package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.repositories.CategoryRepository;
import com.camping.camping.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateProdcutServiceTest {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private UpdateProdcutService updateProdcutService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        productRepository = mock(ProductRepository.class);

        updateProdcutService = new UpdateProdcutService(
                categoryRepository,
                productRepository
        );

    }

    @Test
    @DisplayName("관리자 상품 변경 테스트")
    void updateProductTest(){

        String name = "updatedName";
        Long price = 5000L;
        String description = "updatedDesc";

        Category category = new Category(new Name("testcate1"));

        Product product = new Product(
                category,
                new Name("testProduct"),
                new Money(1000L),
                new Description("testDesc")
        );

        given(categoryRepository
                .findById(category.id()))
                .willReturn(Optional.of(category));

        given(productRepository
                .findById(product.id()))
                .willReturn(Optional.of(product));


        product.updateProduct(
                category,
                new Name(name),
                new Money(price),
                new Description(description)
        );

        productRepository.save(product);

        String res = updateProdcutService.updateProduct(
                product.id(),
                category.id(),
                name,
                price,
                description
        );

        assertThat(res).isEqualTo("success");

        assertThat(product.name().toString()).isEqualTo(name);


    }



}