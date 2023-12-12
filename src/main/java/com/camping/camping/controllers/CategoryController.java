package com.camping.camping.controllers;


import com.camping.camping.applications.GetCategoryService;
import com.camping.camping.dtos.CategoryItemDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final GetCategoryService getCategoryService;

    public CategoryController(GetCategoryService getCategoryService) {
        this.getCategoryService = getCategoryService;
    }

    @GetMapping
    public List<CategoryItemDto> get() {
        return getCategoryService.getCategories();
    }
}
