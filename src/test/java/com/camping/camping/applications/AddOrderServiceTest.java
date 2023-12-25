package com.camping.camping.applications;

import com.camping.camping.domains.Cart;
import com.camping.camping.domains.CartItem;
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
import com.camping.camping.domains.vo.Role;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.dtos.GetCartItemByCartItemIdDto;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.OrderItemRepository;
import com.camping.camping.repositories.OrderRepository;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import com.camping.camping.repositories.UserRepository;
import com.camping.camping.repositories.daos.JdbcCartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.camping.camping.domains.vo.OrderStatus.READY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class AddOrderServiceTest {

    private UserRepository userRepository;
    private CartItemRepository cartItemRepository;

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ProductRepository productRepository;
    private ProductFirstOptionRepository productFirstOptionRepository;
    private ProductSecondOptionRepository productSecondOptionRepository;
    private JdbcCartItemRepository jdbcCartItemRepository;

    private AddOrderService addOrderService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        cartItemRepository = mock(CartItemRepository.class);
        orderRepository = mock(OrderRepository.class);
        orderItemRepository = mock(OrderItemRepository.class);
        productRepository = mock(ProductRepository.class);
        productFirstOptionRepository = mock(ProductFirstOptionRepository.class);
        productSecondOptionRepository = mock(ProductSecondOptionRepository.class);
        jdbcCartItemRepository = mock(JdbcCartItemRepository.class);

        addOrderService = new AddOrderService(
                userRepository,
                cartItemRepository,
                orderRepository,
                orderItemRepository,
                productRepository,
                productFirstOptionRepository,
                productSecondOptionRepository,
                jdbcCartItemRepository
        );

    }


    @Test
    @DisplayName("주문 추가 테스트")
    void addOrderTest() {
        String name = "user10";
        String password = "1234";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        String receiverName = "누군가";
        String address = "서울어딘가";



        User user = new User(
                name,
                encodedePassword,
                Role.ROLE_USER);

        Order newOrder = new Order(
                user,
                new Money(0L),
                new Name(receiverName),
                address,
                READY);


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


        Cart cart = new Cart(user);
        CartItem cartItem = new CartItem(
                cart,
                product,
                firstOption,
                secondOption,
                1
        );

        List<Long> cartItemIds = new ArrayList<>();
        cartItemIds.add(cartItem.id());


        given(userRepository
                .findByName(user.name()))
                .willReturn(Optional.of(user));

        orderRepository.save(newOrder);

        GetCartItemByCartItemIdDto getCartItemByCartItemIdDto = new GetCartItemByCartItemIdDto(
                product.id(),
                product.name().toString(),
                product.price().asLong(),
                firstOption.id(),
                firstOption.name().toString(),
                firstOption.addPrice().asLong(),
                secondOption.id(),
                secondOption.name().toString(),
                secondOption.addPrice().asLong(),
                1
        );


        given(jdbcCartItemRepository
                .findByCartItemId(cartItemIds.get(0)))
                .willReturn(getCartItemByCartItemIdDto);


        given(productRepository
                .findById(getCartItemByCartItemIdDto.productId()))
                .willReturn(Optional.of(product));

        given(productFirstOptionRepository
                .findById(getCartItemByCartItemIdDto.productFirstOptionId()))
                .willReturn(Optional.of(firstOption));

        given(productSecondOptionRepository
                .findById(getCartItemByCartItemIdDto.productSecondOptionId()))
                .willReturn(Optional.of(secondOption));

        OrderItem newOrderItem = new OrderItem(
                newOrder,
                product,
                new Name(getCartItemByCartItemIdDto.productName()),
                new Money(getCartItemByCartItemIdDto.productPrice()),
                firstOption,
                new FirstOptionName(getCartItemByCartItemIdDto.productFirstOptionName()),
                new Money(getCartItemByCartItemIdDto.productFirstOptionPrice()),
                secondOption,
                new SecondOptionName(getCartItemByCartItemIdDto.productSecondOptionName()),
                new Money(getCartItemByCartItemIdDto.productSecondOptionPrice()),
                new Money(2000L),
                getCartItemByCartItemIdDto.quantity(),
                new Money(2000L)
        );

        orderItemRepository.save(newOrderItem);


        given(cartItemRepository
                .findById(cartItem.id()))
                .willReturn(Optional.of(cartItem));

        cartItemRepository.delete(cartItem);

        newOrder.changeTotalPrice(new Money(2000L));
        orderRepository.save(newOrder);

        String res = addOrderService.addOrder(
                name,
                receiverName,
                address,
                cartItemIds);

        assertThat(res).isEqualTo("success");

    }



}