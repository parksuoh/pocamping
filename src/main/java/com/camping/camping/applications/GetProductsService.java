package com.camping.camping.applications;

import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductImage;
import com.camping.camping.dtos.GetProductByCategoryDto;
import com.camping.camping.dtos.ProductImageDto;
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetProductsService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public GetProductsService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }


    public List<GetProductByCategoryDto> getProducts(Long categoryId) {


        List<Product> products = productRepository.findAll();

        return products
                .stream()
                .filter(product -> product.category().id() == categoryId)
                .map(product -> {
                    List<ProductImage> images = productImageRepository.findByProduct_Id(product.id());

                    return new GetProductByCategoryDto(
                            product.id(),
                            product.name().toString(),
                            product.price().asLong(),
                            imagesToDto(images)
                    );
                })
                .toList();

    }

    private List<ProductImageDto> imagesToDto(List<ProductImage> images) {

        return images
                .stream()
                .map(productImage -> new ProductImageDto(productImage.id(), productImage.url()))
                .toList();

    }

}
