package com.Ecommerce.Backend.service;

import com.Ecommerce.Backend.entity.Cart;
import com.Ecommerce.Backend.entity.Order;
import com.Ecommerce.Backend.entity.OrderItem;
import com.Ecommerce.Backend.entity.User;
import com.Ecommerce.Backend.repository.CartItemRepository;
import com.Ecommerce.Backend.repository.OrderRepository;
import com.Ecommerce.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order checkOut(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        List<Cart> cartList = cartItemRepository.findByUser(user);

        if(cartList.isEmpty()){
            throw new RuntimeException("Cart is Empty");
        }

        double totalPrice = 0;
        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());


        List<OrderItem> orderItems = new ArrayList<>();
        for(Cart cart: cartList){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cart.getProduct());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setPrice(cart.getProduct().getPrice()*cart.getQuantity());

            totalPrice += orderItem.getPrice();
            orderItems.add(orderItem);
        }
        order.setTotal_amt(totalPrice);
        order.setItems(orderItems);

        cartItemRepository.deleteAll(cartList);

        return  orderRepository.save(order);
    }

    public List<Order> getUserOrders(Long userId){
        User savedUser  = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not found"));

        return orderRepository.findByUser(savedUser);
    }
}
