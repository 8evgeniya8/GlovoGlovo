package com.example.glovoglovo.service;

import com.example.glovoglovo.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto findProductById(int id);
    List<ProductDto> findAllProducts();
    ProductDto createProduct(ProductDto productDto);
    void updateProductById(int id, ProductDto order);
    void deleteProductById(int id);
    List<ProductDto> findProductsByOrderId(int orderId);
}
