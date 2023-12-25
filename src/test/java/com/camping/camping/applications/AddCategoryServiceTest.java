package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.repositories.CategoryRepository;
import com.camping.camping.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

class AddCategoryServiceTest {

    private CategoryRepository categoryRepository;
    private AddCategoryService addCategoryService;

    @BeforeEach
    void setUp() {

        categoryRepository = mock(CategoryRepository.class);

        addCategoryService = new AddCategoryService(categoryRepository);

    }

    @Test
    @DisplayName("카테고리 생성 테스트")
    void addCategoryTest() {
        String name = "testcate1";

        Category category = new Category(new Name(name));

        categoryRepository.save(category);

        String res = addCategoryService.addCategory(name);

        assertThat(res).isEqualTo("success");

    }



}