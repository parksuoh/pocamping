package com.camping.camping.applications;

import com.camping.camping.domains.Order;
import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.OrderStatus;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateOrderServiceTest {


    private OrderRepository orderRepository;
    private UpdateOrderService updateOrderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        updateOrderService = new UpdateOrderService(orderRepository);

    }

    @Test
    @DisplayName("관리자 주문 변경 테스트")
    void updateOrderTest(){

        String name = "user10";
        String password = "1234";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";
        String orderStatus = "DELIVERY";

        User user = new User(
                name,
                encodedePassword,
                Role.ROLE_USER);

        Order order = new Order(
                user,
                new Money(2000L),
                new Name("testuser"),
                "서울 어딘가",
                OrderStatus.READY);

        OrderStatus status = OrderStatus.isInStatus(orderStatus);

        given(orderRepository
                .findById(order.id()))
                .willReturn(Optional.of(order));

        order.changeOrderStatus(status);

        orderRepository.save(order);

        String res = updateOrderService.updateOrder(order.id(), orderStatus);

        assertThat(res).isEqualTo("success");

        assertThat(order.orderStatus().toString()).isEqualTo(orderStatus);

    }



}