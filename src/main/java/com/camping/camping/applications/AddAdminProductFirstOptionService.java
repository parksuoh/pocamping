package com.camping.camping.applications;


import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.exceptions.ProductNotExist;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddAdminProductFirstOptionService {

    private final ProductRepository productRepository;
    private final ProductFirstOptionRepository productFirstOptionRepository;
    private final ProductSecondOptionRepository productSecondOptionRepository;

    public AddAdminProductFirstOptionService(ProductRepository productRepository, ProductFirstOptionRepository productFirstOptionRepository, ProductSecondOptionRepository productSecondOptionRepository) {
        this.productRepository = productRepository;
        this.productFirstOptionRepository = productFirstOptionRepository;
        this.productSecondOptionRepository = productSecondOptionRepository;
    }

    public String addAdminProductFirstOption(Long productId, String name, Long price) {

        Product product = productRepository
                .findById(productId)
                .orElseThrow(ProductNotExist::new);

        ProductFirstOption productFirstOption = new ProductFirstOption(product, new FirstOptionName(name), new Money(price));

        productFirstOptionRepository.save(productFirstOption);

        ProductSecondOption productSecondOption = new ProductSecondOption(productFirstOption, new SecondOptionName("기본"), new Money(0L));
        productSecondOptionRepository.save(productSecondOption);

        return "success";
    }
}
