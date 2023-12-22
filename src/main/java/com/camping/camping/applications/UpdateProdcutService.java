package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.exceptions.CategoryNotExist;
import com.camping.camping.exceptions.ProductNotExist;
import com.camping.camping.repositories.CategoryRepository;
import com.camping.camping.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateProdcutService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public UpdateProdcutService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public String updateProduct(
            Long productId,
            Long categoryId,
            String name,
            Long price,
            String description
    ) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotExist::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotExist::new);

        product.updateProduct(
                category,
                new Name(name),
                new Money(price),
                new Description(description)
        );

        productRepository.save(product);

        return "success";
    }

}
