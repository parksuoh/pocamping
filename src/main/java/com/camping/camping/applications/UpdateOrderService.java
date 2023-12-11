package com.camping.camping.applications;

import com.camping.camping.domains.Order;
import com.camping.camping.domains.vo.OrderStatus;
import com.camping.camping.exceptions.OrderNotExist;
import com.camping.camping.exceptions.OrderStatusNotMatch;
import com.camping.camping.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateOrderService {

    private final OrderRepository orderRepository;

    public UpdateOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String updateOrder(Long orderId, String orderStatus) {
        if (!orderStatus.equals("READY")
                && !orderStatus.equals("DELIVERY")
                && !orderStatus.equals("COMPLETE")
                && !orderStatus.equals("CANCELED")){
            throw new OrderStatusNotMatch();
        }

        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotExist::new);

        order.changeOrderStatus(OrderStatus.valueOf(orderStatus));
        orderRepository.save(order);
        return "success";
    }
}
