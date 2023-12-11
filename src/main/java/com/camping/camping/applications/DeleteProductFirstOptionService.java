package com.camping.camping.applications;

import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.exceptions.ProductFirstOptionAtLeastOne;
import com.camping.camping.exceptions.ProductFirstOptionNotExist;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeleteProductFirstOptionService {

    private final ProductFirstOptionRepository productFirstOptionRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductSecondOptionRepository productSecondOptionRepository;

    public DeleteProductFirstOptionService(ProductFirstOptionRepository productFirstOptionRepository, CartItemRepository cartItemRepository, ProductSecondOptionRepository productSecondOptionRepository) {
        this.productFirstOptionRepository = productFirstOptionRepository;
        this.cartItemRepository = cartItemRepository;
        this.productSecondOptionRepository = productSecondOptionRepository;
    }

    public String deleteProductFirstOption(Long ProdctFirstOptionId){

        ProductFirstOption productFirstOption = productFirstOptionRepository
                .findById(ProdctFirstOptionId)
                .orElseThrow(ProductFirstOptionNotExist::new);

        Long productId = productFirstOption.product().id();

        List<ProductFirstOption>  productFirstOptions = productFirstOptionRepository.findByProduct_Id(productId);

        if(productFirstOptions.size() <= 1) {
            throw new ProductFirstOptionAtLeastOne();
        }

        productFirstOptionRepository.delete(productFirstOption);

        List<ProductSecondOption> productSecondOptions = productSecondOptionRepository.findByProductFirstOption_Id(ProdctFirstOptionId);

        for (ProductSecondOption productSecondOption : productSecondOptions) {
            productSecondOptionRepository.delete(productSecondOption);
        }

        List<CartItem> cartItems = cartItemRepository.findByProductFirstOption_Id(ProdctFirstOptionId);

        for (CartItem cartItem : cartItems) {
            cartItemRepository.delete(cartItem);
        }

        return "success";
    }

}
