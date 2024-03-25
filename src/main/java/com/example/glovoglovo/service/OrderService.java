package com.example.glovoglovo.service;

import com.example.glovoglovo.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto findOrderById(int id);
    List<OrderDto> findAllOrders();
    OrderDto createOrder(OrderDto orderDto);
    void updateOrder(OrderDto order);
    void deleteOrderById(int id);
}
