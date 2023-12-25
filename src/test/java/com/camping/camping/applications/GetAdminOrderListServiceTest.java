package com.camping.camping.applications;

import com.camping.camping.domains.Category;
import com.camping.camping.domains.Order;
import com.camping.camping.domains.OrderItem;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.OrderStatus;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.dtos.GetOrdersResponseDto;
import com.camping.camping.repositories.OrderItemRepository;
import com.camping.camping.repositories.OrderRepository;
import com.camping.camping.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

class GetAdminOrderListServiceTest {


    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private GetAdminOrderListService getAdminOrderListService;

    @BeforeEach
    void setUp(){

        userRepository = mock(UserRepository.class);
        orderRepository = mock(OrderRepository.class);
        orderItemRepository = mock(OrderItemRepository.class);

        getAdminOrderListService = new GetAdminOrderListService(
                userRepository,
                orderRepository,
                orderItemRepository
        );

    }

    @Test
    @DisplayName("관리자 주문 목록 가져오기 테스트")
    void getOrderListTest(){

        String name = "user10";
        String password = "1234";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";


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

        given(orderRepository
                .findAllByOrderByIdDesc())
                .willReturn(List.of(order));


        Category category = new Category(new Name("testcate1"));


        Product product = new Product(
                category,
                new Name("testProduct"),
                new Money(1000L),
                new Description("testDesc"));


        ProductFirstOption firstOption = new ProductFirstOption(
                product,
                new FirstOptionName("firstName"),
                new Money(100L));

        ProductSecondOption secondOption = new ProductSecondOption(
                firstOption,
                new SecondOptionName("secondeName"),
                new Money(10L));

        OrderItem orderItem = new OrderItem(
                order,
                product,
                product.name(),
                product.price(),
                firstOption,
                firstOption.name(),
                firstOption.addPrice(),
                secondOption,
                secondOption.name(),
                secondOption.addPrice(),
                new Money(2000L),
                1,
                new Money(2000L));


        given(orderItemRepository
                .findByOrder_Id(order.id()))
                .willReturn(List.of(orderItem));

        List<GetOrdersResponseDto> orderList = getAdminOrderListService.getOrderList();

        assertThat(orderList).hasSize(1);

    }



}