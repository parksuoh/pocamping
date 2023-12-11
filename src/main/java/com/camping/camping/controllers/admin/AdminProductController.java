package com.camping.camping.controllers.admin;


import com.camping.camping.applications.AddAdminProductFirstOptionService;
import com.camping.camping.applications.AddAdminProductSecondOptionService;
import com.camping.camping.applications.AddProductImageService;
import com.camping.camping.applications.AddProductService;
import com.camping.camping.applications.DeleteProductFirstOptionService;
import com.camping.camping.applications.DeleteProductImageService;
import com.camping.camping.applications.DeleteProductSecondOptionService;
import com.camping.camping.applications.DeleteProductService;
import com.camping.camping.applications.GetAdminProductDetailService;
import com.camping.camping.applications.GetAdminProductsService;
import com.camping.camping.applications.UpdateProdcutService;
import com.camping.camping.dtos.AddAdminFirstOptionRequestDto;
import com.camping.camping.dtos.AddAdminSecondOptionRequestDto;
import com.camping.camping.dtos.AddProductRequestDto;
import com.camping.camping.dtos.GetProductByCategoryDto;
import com.camping.camping.dtos.GetProductDetailDto;
import com.camping.camping.dtos.UpdateProductRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    private final AddProductService addProductService;
    private final GetAdminProductsService getAdminProductsService;
    private final GetAdminProductDetailService getAdminProductDetailService;

    private final AddAdminProductFirstOptionService addAdminProductFirstOptionService;
    private final AddAdminProductSecondOptionService addAdminProductSecondOptionService;
    private final DeleteProductSecondOptionService deleteProductSecondOptionService;
    private final DeleteProductFirstOptionService deleteProductFirstOptionService;

    private final DeleteProductService deleteProductService;

    private final DeleteProductImageService deleteProductImageService;
    private final AddProductImageService addProductImageService;
    private final UpdateProdcutService updateProdcutService;



    public AdminProductController(AddProductService addProductService, GetAdminProductsService getAdminProductsService, GetAdminProductDetailService getAdminProductDetailService, AddAdminProductFirstOptionService addAdminProductFirstOptionService, AddAdminProductSecondOptionService addAdminProductSecondOptionService, DeleteProductSecondOptionService deleteProductSecondOptionService, DeleteProductFirstOptionService deleteProductFirstOptionService, DeleteProductService deleteProductService, DeleteProductImageService deleteProductImageService, AddProductImageService addProductImageService, UpdateProdcutService updateProdcutService) {
        this.addProductService = addProductService;
        this.getAdminProductsService = getAdminProductsService;
        this.getAdminProductDetailService = getAdminProductDetailService;
        this.addAdminProductFirstOptionService = addAdminProductFirstOptionService;
        this.addAdminProductSecondOptionService = addAdminProductSecondOptionService;
        this.deleteProductSecondOptionService = deleteProductSecondOptionService;
        this.deleteProductFirstOptionService = deleteProductFirstOptionService;
        this.deleteProductService = deleteProductService;
        this.deleteProductImageService = deleteProductImageService;
        this.addProductImageService = addProductImageService;
        this.updateProdcutService = updateProdcutService;
    }



    @PostMapping("/product-image")
    @ResponseStatus(HttpStatus.CREATED)
    public String addImage(
            @RequestPart(value = "productId") Long productId,
            @RequestPart(value = "image") MultipartFile image
    ) throws IOException {

        return addProductImageService.addProductImageService(productId, image);
    }


    @DeleteMapping("/product-image/{productImageId}")
    public String deleteProductImage(@PathVariable Long productImageId) throws IOException {
        return deleteProductImageService.deleteProductImage(productImageId);
    }



    @PostMapping("/first-option")
    @ResponseStatus(HttpStatus.CREATED)
    public String addFirstOption(@Valid @RequestBody AddAdminFirstOptionRequestDto addAdminFirstOptionRequestDto) {

        return addAdminProductFirstOptionService
                .addAdminProductFirstOption(
                        addAdminFirstOptionRequestDto.productId(),
                        addAdminFirstOptionRequestDto.name(),
                        addAdminFirstOptionRequestDto.price()
                );
    }

    @DeleteMapping("/first-option/{productFirstOptionId}")
    public String deleteFirstOption(@PathVariable Long productFirstOptionId) {
        return deleteProductFirstOptionService
                .deleteProductFirstOption(productFirstOptionId);
    }

    @PostMapping("/second-option")
    @ResponseStatus(HttpStatus.CREATED)
    public String addSecondOption(@Valid @RequestBody AddAdminSecondOptionRequestDto addAdminSecondOptionRequestDto) {
        return addAdminProductSecondOptionService
                .addAdminProductSecondOption(
                        addAdminSecondOptionRequestDto.productFirstOptionId(),
                        addAdminSecondOptionRequestDto.name(),
                        addAdminSecondOptionRequestDto.price()
                );
    }

    @DeleteMapping("/second-option/{productSecondOptionId}")
    public String deleteSecondOption(@PathVariable Long productSecondOptionId) {
        return deleteProductSecondOptionService
                .deleteProductSecondOption(productSecondOptionId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String post(
            @RequestPart(value = "AddProductRequestDto") AddProductRequestDto addProductRequestDto,
            @RequestPart(value = "image") MultipartFile image
    ) throws IOException {

        return addProductService.addProduct(
                addProductRequestDto.categoryId(),
                addProductRequestDto.name(),
                addProductRequestDto.price(),
                addProductRequestDto.description(),
                image
        );
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) throws IOException {
        return deleteProductService.deleteProduct(productId);
    }

    @GetMapping("/detail/{productId}")
    public GetProductDetailDto detail(@PathVariable Long productId){

        return getAdminProductDetailService.getProductDetail(productId);
    }

    @GetMapping
    public List<GetProductByCategoryDto> list(){

        return getAdminProductsService.getAdminProducts();
    }

    @PatchMapping
    public String update(@Valid @RequestBody UpdateProductRequestDto updateProductRequestDto) {

        return updateProdcutService.updateProduct(
                updateProductRequestDto.productId(),
                updateProductRequestDto.categoryId(),
                updateProductRequestDto.name(),
                updateProductRequestDto.price(),
                updateProductRequestDto.description()
        );
    }

}
