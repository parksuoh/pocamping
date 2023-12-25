package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.dtos.CategoryItemDto;
import com.camping.camping.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;


class GetAdminCategoriesServiceTest {

    private CategoryRepository categoryRepository;
    private GetAdminCategoriesService getAdminCategoriesService;

    @BeforeEach
    void setUp(){
        categoryRepository = mock(CategoryRepository.class);

        getAdminCategoriesService = new GetAdminCategoriesService(categoryRepository);

    }


    @Test
    @DisplayName("관리자 카테고리 목록 가져오기 테스트")
    void getCategoriesTest(){

        Category cates = new Category(new Name("testCate1"));

        given(categoryRepository
                .findAll(Sort.by(Sort.Direction.DESC, "id")))
                .willReturn(List.of(cates));

        List<CategoryItemDto> categories = getAdminCategoriesService.getCategories();

        assertThat(categories).hasSize(1);

    }


}