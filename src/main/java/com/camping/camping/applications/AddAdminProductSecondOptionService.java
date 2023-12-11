package com.camping.camping.applications;


import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.exceptions.ProductFirstOptionNotExist;
import com.camping.camping.exceptions.ProductNotExist;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddAdminProductSecondOptionService {

    private final ProductFirstOptionRepository productFirstOptionRepository;
    private final ProductSecondOptionRepository productSecondOptionRepository;

    public AddAdminProductSecondOptionService(ProductFirstOptionRepository productFirstOptionRepository, ProductSecondOptionRepository productSecondOptionRepository) {
        this.productFirstOptionRepository = productFirstOptionRepository;
        this.productSecondOptionRepository = productSecondOptionRepository;
    }

    public String addAdminProductSecondOption(Long productFirstOptionId, String name, Long price) {

        ProductFirstOption productFirstOption = productFirstOptionRepository
                .findById(productFirstOptionId)
                .orElseThrow(ProductFirstOptionNotExist::new);

        ProductSecondOption productSecondOption = new ProductSecondOption(productFirstOption, new SecondOptionName(name), new Money(price));

        productSecondOptionRepository.save(productSecondOption);

        return "success";
    }
}
