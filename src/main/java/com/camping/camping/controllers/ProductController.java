package com.camping.camping.controllers;


import com.camping.camping.applications.GetProductDetailService;
import com.camping.camping.applications.GetProductsService;
import com.camping.camping.dtos.GetProductByCategoryDto;
import com.camping.camping.dtos.GetProductDetailDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final GetProductsService getProductsService;
    private final GetProductDetailService getProductDetailService;

    public ProductController(GetProductsService getProductsService, GetProductDetailService getProductDetailService) {
        this.getProductsService = getProductsService;
        this.getProductDetailService = getProductDetailService;
    }

    @GetMapping("/detail/{productId}")
    public GetProductDetailDto getDetail(@PathVariable Long productId) {

        return getProductDetailService.getProductDetail(productId);
    }

    @GetMapping("/{categoryId}")
    public List<GetProductByCategoryDto> get(@PathVariable Long categoryId) {

        return getProductsService.getProducts(categoryId);
    }
}
