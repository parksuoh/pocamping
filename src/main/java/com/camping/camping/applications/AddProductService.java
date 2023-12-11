package com.camping.camping.applications;


import com.camping.camping.aws.S3UploadService;
import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductImage;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.exceptions.CategoryNotExist;
import com.camping.camping.repositories.CategoryRepository;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductImageRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class AddProductService {

    private final S3UploadService s3UploadService;

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductFirstOptionRepository productFirstOptionRepository;
    private final ProductSecondOptionRepository productSecondOptionRepository;

    private final ProductImageRepository productImageRepository;

    public AddProductService(S3UploadService s3UploadService, CategoryRepository categoryRepository, ProductRepository productRepository, ProductFirstOptionRepository productFirstOptionRepository, ProductSecondOptionRepository productSecondOptionRepository, ProductImageRepository productImageRepository) {
        this.s3UploadService = s3UploadService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productFirstOptionRepository = productFirstOptionRepository;
        this.productSecondOptionRepository = productSecondOptionRepository;
        this.productImageRepository = productImageRepository;
    }

    public String addProduct(
            Long categoryId,
            String name,
            Long price,
            String description,
            MultipartFile image
    ) throws IOException {

        String url = s3UploadService.saveFile(image);

        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotExist::new);

        Product product = new Product(category, new Name(name), new Money(price), new Description(description));
        productRepository.save(product);

        ProductImage newProductImage = new ProductImage(product, url);
        productImageRepository.save(newProductImage);

        ProductFirstOption productFirstOption = new ProductFirstOption(product, new FirstOptionName("기본"), new Money(0L));
        productFirstOptionRepository.save(productFirstOption);

        ProductSecondOption productSecondOption = new ProductSecondOption(productFirstOption, new SecondOptionName("기본"), new Money(0L));
        productSecondOptionRepository.save(productSecondOption);

        return "success";
    }
}
