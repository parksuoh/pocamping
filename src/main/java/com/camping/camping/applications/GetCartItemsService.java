package com.camping.camping.applications;

import com.camping.camping.domains.Cart;
import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.User;
import com.camping.camping.dtos.CartItemsResponseDto;
import com.camping.camping.dtos.GetCartItemsDto;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.CartRepository;
import com.camping.camping.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetCartItemsService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public GetCartItemsService(UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItemsResponseDto> getCartItems(String name) {

        User user = userRepository
                .findByName(name)
                .orElseThrow(NameNotExist::new);

        Cart cart = cartRepository.findTop1ByUser_Id(user.id());

        List<GetCartItemsDto> cartItems = cartItemRepository.findByCartId(cart.id());

        System.out.println(cartItems.size());

        return cartItems
                .stream()
                .map(cartItem -> {
                    Long itemUnitPrice =  cartItem.price() + cartItem.productFirstAddPrice() + cartItem.productSecondAddPrice();
                    Long itemTotalPrice =  itemUnitPrice * cartItem.quantity();

                    return new CartItemsResponseDto(
                            cartItem.cartItemId(),
                            cartItem.quantity(),
                            cartItem.productId(),
                            cartItem.name().toString(),
                            cartItem.price(),
                            cartItem.productFirstOptionId(),
                            cartItem.productFirstOptionName().toString(),
                            cartItem.productFirstAddPrice(),
                            cartItem.productSecondOptionId(),
                            cartItem.productSecondOptionName().toString(),
                            cartItem.productSecondAddPrice(),
                            itemUnitPrice,
                            itemTotalPrice
                    );
                }).toList();


    }

}
