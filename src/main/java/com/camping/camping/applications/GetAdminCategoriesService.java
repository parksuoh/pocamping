package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.dtos.CategoryItemDto;
import com.camping.camping.repositories.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetAdminCategoriesService {

    private final CategoryRepository categoryRepository;

    public GetAdminCategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryItemDto> getCategories() {

        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return categories
                .stream()
                .map(category -> new CategoryItemDto(category.id(), category.name().toString()))
                .toList();
    }

}
