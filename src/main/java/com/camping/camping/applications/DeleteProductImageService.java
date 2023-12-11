package com.camping.camping.applications;


import com.camping.camping.aws.S3DeleteService;
import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.ProductImage;
import com.camping.camping.exceptions.ProductImageNotExist;
import com.camping.camping.repositories.ProductImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
public class DeleteProductImageService {

    private final S3DeleteService s3DeleteService;

    private final ProductImageRepository productImageRepository;

    public DeleteProductImageService(S3DeleteService s3DeleteService, ProductImageRepository productImageRepository) {
        this.s3DeleteService = s3DeleteService;
        this.productImageRepository = productImageRepository;
    }

    public String deleteProductImage(Long productImageId) throws IOException {

        ProductImage productImage = productImageRepository
                .findById(productImageId)
                .orElseThrow(ProductImageNotExist::new);

        s3DeleteService.deleteFile(productImage.url());

        productImageRepository.delete(productImage);

        return "success";
    }
}
