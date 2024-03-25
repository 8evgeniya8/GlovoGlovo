package com.example.glovoglovo.service;

import com.example.glovoglovo.dto.OrderDto;
import com.example.glovoglovo.dto.ProductDto;
import com.example.glovoglovo.mapper.OrderMapper;
import com.example.glovoglovo.model.Order;
import com.example.glovoglovo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto findOrderById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        Order order = orderOptional.orElse(null);
        if(order == null) log.error("Such an order does not exist");
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderMapper.toDtoList(orderList);
    }


    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order = orderRepository.save(order);
        log.debug("Created new order : " + order.getId());
        return orderMapper.toDto(order);
    }


    @Override
    public void updateOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        orderRepository.save(order);
        log.debug("Updated order : " + order.getId());
    }

    @Override
    public void deleteOrderById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        Order order = orderOptional.orElse(null);
        if(order == null) log.error("No order found");

        List<ProductDto> productsOfOrder = productService.findProductsByOrderId(id);
        for (ProductDto product : productsOfOrder) {
            productService.deleteProductById(product.getId());
        }
        log.trace("Deleted order: " + id);
        orderRepository.deleteById(id);
    }
}