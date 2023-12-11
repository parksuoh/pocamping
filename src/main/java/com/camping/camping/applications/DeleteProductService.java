package com.camping.camping.applications;


import com.camping.camping.aws.S3DeleteService;
import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductImage;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.exceptions.ProductNotExist;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class DeleteProductService {

    private final ProductRepository productRepository;
    private final ProductFirstOptionRepository productFirstOptionRepository;
    private final ProductSecondOptionRepository productSecondOptionRepository;
    private final ProductImageRepository productImageRepository;
    private final S3DeleteService s3DeleteService;

    private final CartItemRepository cartItemRepository;
    public DeleteProductService(ProductRepository productRepository, ProductFirstOptionRepository productFirstOptionRepository, ProductSecondOptionRepository productSecondOptionRepository, ProductImageRepository productImageRepository, S3DeleteService s3DeleteService, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.productFirstOptionRepository = productFirstOptionRepository;
        this.productSecondOptionRepository = productSecondOptionRepository;
        this.productImageRepository = productImageRepository;
        this.s3DeleteService = s3DeleteService;
        this.cartItemRepository = cartItemRepository;
    }

    public String deleteProduct(Long productId) throws IOException {

        Product product = productRepository.findById(productId).orElseThrow(ProductNotExist::new);
        List<ProductFirstOption> productFirstOptions = productFirstOptionRepository.findByProduct_Id(productId);
        List<ProductImage> productImages = productImageRepository.findByProduct_Id(productId);
        List<CartItem> cartItems = cartItemRepository.findByProduct_Id(productId);

        deleteFirstOptions(productFirstOptions);
        deleteProductImages(productImages);
        deleteCartItem(cartItems);
        productRepository.delete(product);

        return "success";
    }


    private void deleteFirstOptions(List<ProductFirstOption> productFirstOptions) {
        for (ProductFirstOption productFirstOption : productFirstOptions) {
            List<ProductSecondOption> productSecondOptions = productSecondOptionRepository.findByProductFirstOption_Id(productFirstOption.id());

            deleteSecondOptions(productSecondOptions);

            productFirstOptionRepository.delete(productFirstOption);

        }
    }

    private void deleteSecondOptions(List<ProductSecondOption> productSecondOptions) {
        for (ProductSecondOption productSecondOption : productSecondOptions) {
            productSecondOptionRepository.delete(productSecondOption);
        }

    }

    private void deleteProductImages(List<ProductImage> productImages) throws IOException {
        for (ProductImage productImage : productImages) {
            s3DeleteService.deleteFile(productImage.url());
            productImageRepository.delete(productImage);
        }
    }

    private void deleteCartItem(List<CartItem> cartItems){
        for (CartItem cartItem : cartItems) {
            cartItemRepository.delete(cartItem);
        }
    }

}
