package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.exceptions.CategoryNotExist;
import com.camping.camping.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteCategoryService {

    private final CategoryRepository categoryRepository;


    public DeleteCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(CategoryNotExist::new);

        categoryRepository.delete(category);

        return "success";
    }



}
