package com.camping.camping.applications;

import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductImage;
import com.camping.camping.dtos.GetProductByCategoryDto;
import com.camping.camping.dtos.ProductImageDto;
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetAdminProductsService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public GetAdminProductsService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public List<GetProductByCategoryDto> getAdminProducts() {

        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return products
                .stream()
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
