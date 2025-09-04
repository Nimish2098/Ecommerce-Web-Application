package com.Ecommerce.Backend.controller;


import com.Ecommerce.Backend.entity.Order;
import com.Ecommerce.Backend.repository.OrderRepository;
import com.Ecommerce.Backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/checkout/{userId}")
    public Order checkOut(@PathVariable Long userId){
        return orderService.checkOut(userId);
    }

    @GetMapping("/{userId}")
    public List<Order> getUserOrder(@PathVariable Long userId){
        return orderService.getUserOrders(userId);
    }

}
