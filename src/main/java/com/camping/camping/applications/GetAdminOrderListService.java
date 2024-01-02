package com.camping.camping.applications;


import com.camping.camping.domains.Order;
import com.camping.camping.domains.OrderItem;
import com.camping.camping.domains.User;
import com.camping.camping.dtos.GetOrderItemDto;
import com.camping.camping.dtos.GetOrdersResponseDto;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.repositories.OrderItemRepository;
import com.camping.camping.repositories.OrderRepository;
import com.camping.camping.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetAdminOrderListService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public GetAdminOrderListService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<GetOrdersResponseDto> getOrderList() {

        List<Order> orders = orderRepository.findAllByOrderByIdDesc();

        return orders
                .stream()
                .map(order -> {
                    List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(order.id());
                    List<GetOrderItemDto> getOrderItemDtos = orderItemToDto(orderItems);

                    return new GetOrdersResponseDto(
                            order.id(),
                            order.totalPrice().asLong(),
                            order.receiverName().toString(),
                            order.address(),
                            order.orderStatus().toString(),
                            getOrderItemDtos);})
                .toList();


    }

    private List<GetOrderItemDto> orderItemToDto(List<OrderItem> orderItems){

        return orderItems
                .stream()
                .map(orderItem -> new GetOrderItemDto(
                    orderItem.id(),
                    orderItem.productName().toString(),
                    orderItem.productPrice().asLong(),
                    orderItem.productFirstOptionName().toString(),
                    orderItem.productFirstOptionPrice().asLong(),
                    orderItem.productSecondOptionName().toString(),
                    orderItem.productSecondOptionPrice().asLong(),
                    orderItem.unitPrice().asLong(),
                    orderItem.quantity(),
                    orderItem.totalPrice().asLong()
            )).toList();
    }

}
