package com.camping.camping.applications;

import com.camping.camping.domains.Order;
import com.camping.camping.domains.vo.OrderStatus;
import com.camping.camping.exceptions.OrderNotExist;
import com.camping.camping.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateOrderService {

    private final OrderRepository orderRepository;

    public UpdateOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String updateOrder(Long orderId, String orderStatus) {

        OrderStatus status = OrderStatus.isInStatus(orderStatus);

        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotExist::new);

        if(status.toString().equals("READY")){
            order.toReady();
        } else if (status.toString().equals("DELIVERY")) {
            order.toDelivery();
        } else if (status.toString().equals("COMPLETE")) {
            order.toComplete();
        } else if (status.toString().equals("CANCELED")) {
            order.toCanceled();
        }

        orderRepository.save(order);
        return "success";
    }
}
