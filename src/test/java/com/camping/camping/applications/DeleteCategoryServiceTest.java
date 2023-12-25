package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteCategoryServiceTest {

    private CategoryRepository categoryRepository;
    private DeleteCategoryService deleteCategoryService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);

        deleteCategoryService = new DeleteCategoryService(categoryRepository);
    }

    @Test
    @DisplayName("카테고리 삭제 테스트")
    void deleteCategoryTest() {

        Category category = new Category(new Name("testcate1"));

        given(categoryRepository
                .findById(category.id()))
                .willReturn(Optional.of(category));

        categoryRepository.delete(category);

        String res = deleteCategoryService.deleteCategory(category.id());

        assertThat(res).isEqualTo("success");

    }

}