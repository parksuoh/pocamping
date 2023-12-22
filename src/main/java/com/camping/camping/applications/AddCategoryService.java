package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddCategoryService {

    private final CategoryRepository categoryRepository;

    public AddCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String addCategory(String name){

        Category category = new Category(new Name(name));

        categoryRepository.save(category);

        return "success";
    }
}
