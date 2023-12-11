package com.camping.camping.applications;


import com.camping.camping.domains.Cart;
import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.domains.User;
import com.camping.camping.exceptions.CartItemNotExist;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.exceptions.ProductFirstOptionNotExist;
import com.camping.camping.exceptions.ProductNotExist;
import com.camping.camping.exceptions.ProductOptionNotMatch;
import com.camping.camping.exceptions.ProductSecondOptionNotExist;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.CartRepository;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import com.camping.camping.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddCartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ProductFirstOptionRepository productFirstOptionRepository;
    private final ProductSecondOptionRepository productSecondOptionRepository;

    public AddCartService(UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, ProductFirstOptionRepository productFirstOptionRepository, ProductSecondOptionRepository productSecondOptionRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.productFirstOptionRepository = productFirstOptionRepository;
        this.productSecondOptionRepository = productSecondOptionRepository;
    }

    public String addCart(String name, Long productId, Long productFirstOptionId, Long productSecondOptionId, Integer quantity) {

        User user = userRepository
                .findByName(name)
                .orElseThrow(NameNotExist::new);

        Cart cart = cartRepository.findTop1ByUser_Id(user.id());

        Product product = productRepository
                .findById(productId)
                .orElseThrow(ProductNotExist::new);

        ProductFirstOption productFirstOption = productFirstOptionRepository
                .findById(productFirstOptionId)
                .orElseThrow(ProductFirstOptionNotExist::new);

        ProductSecondOption productSecondOption = productSecondOptionRepository
                .findById(productSecondOptionId)
                .orElseThrow(ProductSecondOptionNotExist::new);



        if (
                product.id() !=  productFirstOption.product().id() ||
                productFirstOption.id() != productSecondOption.productFirstOption().id()
        ){
            throw new ProductOptionNotMatch();
        }

        CartItem cartItem = cartItemRepository
                .findByCart_IdAndProduct_IdAndProductFirstOption_IdAndProductSecondOption_Id(
                        cart.id(),
                        productId,
                        productFirstOptionId,
                        productSecondOptionId);

        if(cartItem == null) {
            cartItem = new CartItem(cart, product, productFirstOption, productSecondOption, quantity);
        } else cartItem.addQuantity(quantity);


        cartItemRepository.save(cartItem);

        return "success";
    }
}
