package com.example.glovoglovo.service;

import com.example.glovoglovo.dto.OrderDto;
import com.example.glovoglovo.mapper.OrderMapper;
import com.example.glovoglovo.model.Order;
import com.example.glovoglovo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private final int orderId = 1;
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @Mock
    private OrderMapper orderMapper;
    @Mock
    private Order order;
    @Mock
    private OrderDto orderDto;

    @Test
    void findOrderByIdTest() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderMapper.toDto(order)).thenReturn(orderDto);

        OrderDto result = orderService.findOrderById(orderId);

        assertNotNull(result);
        assertEquals(orderDto, result);
        verify(orderRepository).findById(orderId);
        verify(orderMapper).toDto(order);
    }

    @Test
    void findAllOrdersTest() {
        List<Order> orders = Arrays.asList(order);
        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.toDtoList(orders)).thenReturn(Arrays.asList(orderDto));

        List<OrderDto> result = orderService.findAllOrders();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(orderDto, result.get(0));
        verify(orderRepository).findAll();
        verify(orderMapper).toDtoList(orders);
    }

    @Test
    void createOrderTest() {
        when(orderMapper.toEntity(orderDto)).thenReturn(order);

        orderService.createOrder(orderDto);

        verify(orderRepository).save(order);
        verify(orderMapper).toEntity(orderDto);
    }

    @Test
    void updateOrderTest() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderId);
        Order order = new Order();
        when(orderMapper.toEntity(any(OrderDto.class))).thenReturn(order);

        orderService.updateOrder(orderDto);

        verify(orderRepository).save(order);
        verify(orderMapper).toEntity(orderDto);
    }

    @Test
    void deleteOrderByIdTest() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(productService.findProductsByOrderId(orderId)).thenReturn(new ArrayList<>());

        orderService.deleteOrderById(orderId);

        verify(orderRepository).findById(orderId);
        verify(orderRepository).deleteById(orderId);
        verify(productService).findProductsByOrderId(orderId);
    }
}