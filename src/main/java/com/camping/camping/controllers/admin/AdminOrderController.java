package com.camping.camping.controllers.admin;

import com.camping.camping.applications.GetAdminOrderListService;
import com.camping.camping.applications.UpdateOrderService;
import com.camping.camping.dtos.GetOrdersResponseDto;
import com.camping.camping.dtos.UpdateOrderRequstDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/order")
public class AdminOrderController {

    private final GetAdminOrderListService getAdminOrderListService;
    private final UpdateOrderService updateOrderService;

    public AdminOrderController(GetAdminOrderListService getAdminOrderListService, UpdateOrderService updateOrderService) {
        this.getAdminOrderListService = getAdminOrderListService;
        this.updateOrderService = updateOrderService;
    }

    @GetMapping
    public List<GetOrdersResponseDto> get(){

        return getAdminOrderListService.getOrderList();
    }

    @PatchMapping
    public String update(@Valid @RequestBody UpdateOrderRequstDto updateOrderRequstDto) {
        return updateOrderService
                .updateOrder(
                        updateOrderRequstDto.orderId(),
                        updateOrderRequstDto.orderStatus()
                );
    }

}
