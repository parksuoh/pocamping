package com.camping.camping.applications;

import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.exceptions.ProductSecondOptionAtLeastOne;
import com.camping.camping.exceptions.ProductSecondOptionNotExist;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeleteProductSecondOptionService {

    private final ProductSecondOptionRepository productSecondOptionRepository;
    private final CartItemRepository cartItemRepository;

    public DeleteProductSecondOptionService(ProductSecondOptionRepository productSecondOptionRepository, CartItemRepository cartItemRepository) {
        this.productSecondOptionRepository = productSecondOptionRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public String deleteProductSecondOption(Long ProdctSecondOptionId){

        ProductSecondOption productSecondOption = productSecondOptionRepository
                .findById(ProdctSecondOptionId)
                .orElseThrow(ProductSecondOptionNotExist::new);

        Long productFirstOptionId = productSecondOption.productFirstOption().id();

        List<ProductSecondOption> productFirstOptions = productSecondOptionRepository.findByProductFirstOption_Id(productFirstOptionId);

        if(productFirstOptions.size() <= 1){
            throw new ProductSecondOptionAtLeastOne();
        }

        productSecondOptionRepository.delete(productSecondOption);

        List<CartItem> cartItems = cartItemRepository.findByProductSecondOption_Id(ProdctSecondOptionId);

        for (CartItem cartItem : cartItems) {
            cartItemRepository.delete(cartItem);
        }

        return "success";
    }

}
