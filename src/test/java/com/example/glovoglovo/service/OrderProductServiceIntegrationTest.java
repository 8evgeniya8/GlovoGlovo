package com.example.glovoglovo.service;

import com.example.glovoglovo.dto.OrderDto;
import com.example.glovoglovo.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OrderProductServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Test
    public void testDeleteOrderAndVerifyProductsDeleted() {
        ProductDto newProduct = ProductDto.builder()
                .name("Test Product")
                .cost(100.0)
                .build();
        ProductDto savedProduct = productService.createProduct(newProduct);

        assertNotEquals(0, savedProduct.getId());

        OrderDto newOrder = OrderDto.builder()
                .date(LocalDate.now())
                .cost(100.0)
                .build();
        OrderDto savedOrder = orderService.createOrder(newOrder);

        assertNotEquals(0, savedOrder.getId());

        orderService.deleteOrderById(savedOrder.getId());

        OrderDto deletedOrder = orderService.findOrderById(savedOrder.getId());
        assertNull(deletedOrder);

        List<ProductDto> productsAfterDeletion = productService.findProductsByOrderId(savedOrder.getId());
        assertTrue(productsAfterDeletion.isEmpty());
    }

    @Test
    public void testCreateOrderWithProducts() {
        OrderDto newOrder = OrderDto.builder()
                .date(LocalDate.now())
                .cost(250.0)
                .build();
        OrderDto savedOrder = orderService.createOrder(newOrder);

        ProductDto product1 = ProductDto.builder()
                .name("Product 1")
                .cost(100.0)
                .orderId(savedOrder.getId())
                .build();
        ProductDto savedProduct1 = productService.createProduct(product1);

        ProductDto product2 = ProductDto.builder()
                .name("Product 2")
                .cost(150.0)
                .orderId(savedOrder.getId())
                .build();
        ProductDto savedProduct2 = productService.createProduct(product2);

        List<ProductDto> products = productService.findProductsByOrderId(savedOrder.getId());
        assertTrue(products.containsAll(Arrays.asList(savedProduct1, savedProduct2)));
    }

}
