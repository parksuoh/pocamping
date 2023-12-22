package com.camping.camping.applications;

import com.camping.camping.domains.Cart;
import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.User;
import com.camping.camping.exceptions.CartAndCartItemNotMatch;
import com.camping.camping.exceptions.CartItemNotExist;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.CartRepository;
import com.camping.camping.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateCartItemService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public UpdateCartItemService(UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public String updateCartItem(String name, Long cartItemId, Integer quantity) {
        User user = userRepository
                .findByName(name)
                .orElseThrow(NameNotExist::new);

        Cart cart = cartRepository.findTop1ByUser_Id(user.id());

        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(CartItemNotExist::new);

        if (cart.id() != cartItem.cart().id()){
            throw new CartAndCartItemNotMatch();
        }

        cartItem.changeQuantity(quantity);
        cartItemRepository.save(cartItem);

        return "success";
    }

}
