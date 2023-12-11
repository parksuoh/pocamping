package com.camping.camping.controllers;


import com.camping.camping.applications.AddCartService;
import com.camping.camping.applications.DeleteCartItemService;
import com.camping.camping.applications.GetCartItemsService;
import com.camping.camping.applications.UpdateCartItemService;
import com.camping.camping.dtos.AddCartRequestDto;
import com.camping.camping.dtos.AuthUserDto;
import com.camping.camping.dtos.CartItemsResponseDto;
import com.camping.camping.dtos.UpdateCartItemRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final AddCartService addCartService;
    private final GetCartItemsService getCartItemsService;
    private final DeleteCartItemService deleteCartItemService;
    private final UpdateCartItemService updateCartItemService;

    public CartController(AddCartService addCartService, GetCartItemsService getCartItemsService, DeleteCartItemService deleteCartItemService, UpdateCartItemService updateCartItemService) {
        this.addCartService = addCartService;
        this.getCartItemsService = getCartItemsService;
        this.deleteCartItemService = deleteCartItemService;
        this.updateCartItemService = updateCartItemService;
    }


    @GetMapping
    public List<CartItemsResponseDto> get(Authentication authentication) {
        AuthUserDto authUser = (AuthUserDto) authentication.getPrincipal();

        return getCartItemsService.getCartItems(authUser.name());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addCart(
            Authentication authentication,
            @Valid @RequestBody AddCartRequestDto addCartRequestDto
    ) {
        AuthUserDto authUser = (AuthUserDto) authentication.getPrincipal();
        Long productId = addCartRequestDto.productId();
        Long productFirstOptionId = addCartRequestDto.productFirstOptionId();
        Long productSecondOptionId = addCartRequestDto.productSecondOptionId();
        Integer quantity = addCartRequestDto.quantity();
        String res = addCartService.addCart(
                authUser.name(),
                productId,
                productFirstOptionId,
                productSecondOptionId,
                quantity);

        return res;
    }


    @DeleteMapping("/{cartItemId}")
    public String delete(
            Authentication authentication,
            @PathVariable Long cartItemId
    ) {
        AuthUserDto authUser = (AuthUserDto) authentication.getPrincipal();
        return deleteCartItemService.deleteCartItem(
                authUser.name(),
                cartItemId
        );
    }

    @PatchMapping
    public String update(
            Authentication authentication,
            @Valid @RequestBody UpdateCartItemRequestDto updateCartItemRequestDto
    ) {

        AuthUserDto authUser = (AuthUserDto) authentication.getPrincipal();

        return updateCartItemService.updateCartItem(
                authUser.name(),
                updateCartItemRequestDto.cartItemId(),
                updateCartItemRequestDto.quantity()
        );
    }

}
