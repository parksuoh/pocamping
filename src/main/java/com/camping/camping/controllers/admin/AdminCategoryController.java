package com.camping.camping.controllers.admin;

import com.camping.camping.applications.AddCategoryService;
import com.camping.camping.applications.DeleteCategoryService;
import com.camping.camping.applications.GetAdminCategoriesService;
import com.camping.camping.dtos.AddCategoryRequestDto;
import com.camping.camping.dtos.CategoryItemDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/category")
public class AdminCategoryController {

    private final GetAdminCategoriesService getAdminCategoriesService;
    private final AddCategoryService addCategoryService;
    private final DeleteCategoryService deleteCategoryService;

    public AdminCategoryController(GetAdminCategoriesService getAdminCategoriesService, AddCategoryService addCategoryService, DeleteCategoryService deleteCategoryService) {
        this.getAdminCategoriesService = getAdminCategoriesService;
        this.addCategoryService = addCategoryService;
        this.deleteCategoryService = deleteCategoryService;
    }

    @GetMapping
    public List<CategoryItemDto> get(){

        return getAdminCategoriesService.getCategories();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String post(@Valid @RequestBody AddCategoryRequestDto addCategoryRequestDto) {
        return addCategoryService.addCategory(addCategoryRequestDto.categoryName());
    }

    @DeleteMapping("/{categoryId}")
    public String delete(@PathVariable Long categoryId) {

        return deleteCategoryService.deleteCategory(categoryId);
    }

}
