package com.camping.camping.applications;

import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductImage;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.dtos.FindProductSecondOptionDto;
import com.camping.camping.dtos.GetProductDetailDto;
import com.camping.camping.dtos.GetProductFitstOptionDto;
import com.camping.camping.dtos.GetProductSecondOptionDto;
import com.camping.camping.dtos.ProductImageDto;
import com.camping.camping.exceptions.ProductNotExist;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetAdminProductDetailService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    private final ProductFirstOptionRepository productFirstOptionRepository;
    private final ProductSecondOptionRepository productSecondOptionRepository;

    public GetAdminProductDetailService(ProductRepository productRepository, ProductImageRepository productImageRepository, ProductFirstOptionRepository productFirstOptionRepository, ProductSecondOptionRepository productSecondOptionRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.productFirstOptionRepository = productFirstOptionRepository;
        this.productSecondOptionRepository = productSecondOptionRepository;
    }

    public GetProductDetailDto getProductDetail(Long productId){

        Product product = productRepository
                .findById(productId)
                .orElseThrow(ProductNotExist::new);

        List<ProductImage> images = productImageRepository.findByProduct_Id(product.id());


        return new GetProductDetailDto(
                product.id(),
                product.category().id(),
                product.name().toString(),
                product.price().asLong(),
                product.description().toString(),
                imagesToDto(images),
                firstOptionToDto(product.id()));
    }

    private List<GetProductSecondOptionDto> secondOptionToDto(Long firstOptionId) {
        List<ProductSecondOption> productSecondOptions = productSecondOptionRepository.findByProductFirstOption_Id(firstOptionId);

        return productSecondOptions
                .stream()
                .map(secondOption ->
                new GetProductSecondOptionDto(
                        secondOption.id(),
                        secondOption.name().toString(),
                        secondOption.addPrice().asLong())).toList();
    }

    private List<GetProductFitstOptionDto> firstOptionToDto(Long productId) {
        List<ProductFirstOption> productFirstOptions = productFirstOptionRepository.findByProduct_Id(productId);


        return productFirstOptions
                .stream()
                .map(firstOption -> {
                    Long id = firstOption.id();
                    return new GetProductFitstOptionDto(
                            id,
                            firstOption.name().toString(),
                            firstOption.addPrice().asLong(),
                            secondOptionToDto(id));})
                .toList();
    }

    private List<ProductImageDto> imagesToDto(List<ProductImage> images) {

        return images
                .stream()
                .map(productImage -> new ProductImageDto(productImage.id(), productImage.url()))
                .toList();

    }

}
