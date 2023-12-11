package com.camping.camping.controllers;


import com.camping.camping.applications.AddOrderService;
import com.camping.camping.applications.GetOrderListService;
import com.camping.camping.dtos.AddOrderRequestDto;
import com.camping.camping.dtos.AuthUserDto;
import com.camping.camping.dtos.GetOrdersResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final AddOrderService addOrderService;
    private final GetOrderListService getOrderListService;

    public OrderController(AddOrderService addOrderService, GetOrderListService getOrderListService) {
        this.addOrderService = addOrderService;
        this.getOrderListService = getOrderListService;
    }

    @GetMapping
    public List<GetOrdersResponseDto> get(Authentication authentication) {
        AuthUserDto authUser = (AuthUserDto) authentication.getPrincipal();

        return getOrderListService.getOrderList(authUser.name());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String post(
            Authentication authentication,
            @Valid @RequestBody AddOrderRequestDto addOrderRequestDto
    ) {
        AuthUserDto authUser = (AuthUserDto) authentication.getPrincipal();

        return addOrderService.addOrder(
                authUser.name(),
                addOrderRequestDto.receiverName(),
                addOrderRequestDto.address(),
                addOrderRequestDto.cartItemIds()
        );

    }

}
