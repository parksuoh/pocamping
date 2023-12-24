package com.camping.camping.applications;

import com.camping.camping.domains.Cart;
import com.camping.camping.domains.Category;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.dtos.CategoryItemDto;
import com.camping.camping.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

class GetCategoryServiceTest {

    private CategoryRepository categoryRepository;

    private GetCategoryService getCategoryService;

    @BeforeEach
    void setUp(){

        categoryRepository = mock(CategoryRepository.class);

        getCategoryService = new GetCategoryService(
                categoryRepository
        );

    }

    @Test
    @DisplayName("카테고리 목록 가져오기 테스트")
    void getCartegoryTest() {

        Category cates = new Category(new Name("testCate1"));

        given(categoryRepository.findAll()).willReturn(List.of(cates));

        List<CategoryItemDto> categories = getCategoryService.getCategories();

        assertThat(categories).hasSize(1);

    }



}