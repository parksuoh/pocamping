package com.camping.camping.applications;


import com.camping.camping.domains.CartItem;
import com.camping.camping.domains.Order;
import com.camping.camping.domains.OrderItem;
import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import com.camping.camping.domains.ProductSecondOption;
import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.SecondOptionName;
import com.camping.camping.dtos.GetCartItemByCartItemIdDto;
import com.camping.camping.exceptions.CartItemNotExist;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.exceptions.ProductFirstOptionNotExist;
import com.camping.camping.exceptions.ProductNotExist;
import com.camping.camping.exceptions.ProductSecondOptionNotExist;
import com.camping.camping.repositories.CartItemRepository;
import com.camping.camping.repositories.OrderItemRepository;
import com.camping.camping.repositories.OrderRepository;
import com.camping.camping.repositories.ProductFirstOptionRepository;
import com.camping.camping.repositories.ProductRepository;
import com.camping.camping.repositories.ProductSecondOptionRepository;
import com.camping.camping.repositories.UserRepository;
import com.camping.camping.repositories.daos.JdbcCartItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.camping.camping.domains.vo.OrderStatus.READY;

@Service
@Transactional
public class AddOrderService {

    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ProductFirstOptionRepository productFirstOptionRepository;
    private final ProductSecondOptionRepository productSecondOptionRepository;
    private final JdbcCartItemRepository jdbcCartItemRepository;



    public AddOrderService(UserRepository userRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository, ProductFirstOptionRepository productFirstOptionRepository, ProductSecondOptionRepository productSecondOptionRepository, JdbcCartItemRepository jdbcCartItemRepository) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.productFirstOptionRepository = productFirstOptionRepository;
        this.productSecondOptionRepository = productSecondOptionRepository;
        this.jdbcCartItemRepository = jdbcCartItemRepository;
    }

    public String addOrder(String name, String receiverName, String address, List<Long> cartItemIds){
        Long cartTotalPrice = 0L;
        User user = userRepository
                .findByName(name)
                .orElseThrow(NameNotExist::new);

        Order newOrder = new Order(
                user,
                new Money(0L),
                new Name(receiverName),
                address,
                READY);

        orderRepository.save(newOrder);


        for (Long cartItemId : cartItemIds) {
            GetCartItemByCartItemIdDto result = jdbcCartItemRepository.findByCartItemId(cartItemId);


            Long unitPrice = result.productPrice()
                    + result.productFirstOptionPrice()
                    + result.productSecondOptionPrice();

            Long totalPrice = unitPrice * result.quantity();
            cartTotalPrice += totalPrice;

            Product product = productRepository.findById(result.productId())
                    .orElseThrow(ProductNotExist::new);

            ProductFirstOption productFirstOption = productFirstOptionRepository.findById(result.productFirstOptionId())
                    .orElseThrow(ProductFirstOptionNotExist::new);

            ProductSecondOption productSecondOption = productSecondOptionRepository.findById(result.productSecondOptionId())
                    .orElseThrow(ProductSecondOptionNotExist::new);

            OrderItem newOrderItem = new OrderItem(
                    newOrder,
                    product,
                    new Name(result.productName()),
                    new Money(result.productPrice()),
                    productFirstOption,
                    new FirstOptionName(result.productFirstOptionName()),
                    new Money(result.productFirstOptionPrice()),
                    productSecondOption,
                    new SecondOptionName(result.productSecondOptionName()),
                    new Money(result.productSecondOptionPrice()),
                    new Money(unitPrice),
                    result.quantity(),
                    new Money(totalPrice)
            );

            orderItemRepository.save(newOrderItem);

             CartItem cartItem = cartItemRepository.findById(cartItemId)
                     .orElseThrow(CartItemNotExist::new);

             cartItemRepository.delete(cartItem);
        }

        newOrder.changeTotalPrice(new Money(cartTotalPrice));
        orderRepository.save(newOrder);

        return "success";
    }

}
