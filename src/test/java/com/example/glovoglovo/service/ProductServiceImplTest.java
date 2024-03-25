package com.example.glovoglovo.service;

import com.example.glovoglovo.dto.ProductDto;
import com.example.glovoglovo.mapper.ProductMapper;
import com.example.glovoglovo.model.Product;
import com.example.glovoglovo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findProductByIdTest() {
        Product product = new Product();
        ProductDto productDto = new ProductDto();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDto);

        ProductDto foundProduct = productService.findProductById(1);

        assertNotNull(foundProduct);
        verify(productRepository).findById(1);
        verify(productMapper).toDto(product);
    }

    @Test
    void findAllProductsTest() {
        List<Product> products = Collections.singletonList(new Product());
        when(productRepository.findAll()).thenReturn(products);
        List<ProductDto> productDtos = Collections.singletonList(new ProductDto());
        when(productMapper.toDtoList(products)).thenReturn(productDtos);

        List<ProductDto> foundProducts = productService.findAllProducts();

        assertFalse(foundProducts.isEmpty());
        verify(productRepository).findAll();
        verify(productMapper).toDtoList(products);
    }

    @Test
    void createProductTest() {
        ProductDto productDto = new ProductDto();
        Product product = new Product();
        when(productMapper.toEntity(any(ProductDto.class))).thenReturn(product);
        doNothing().when(productRepository).save(any(Product.class));

        productService.createProduct(productDto);

        verify(productRepository).save(product);
        verify(productMapper).toEntity(productDto);
    }

    @Test
    void updateProductByIdTest() {
        ProductDto productDto = new ProductDto();
        Product product = new Product();
        when(productMapper.toEntity(any(ProductDto.class))).thenReturn(product);
        doNothing().when(productRepository).save(any(Product.class));

        productService.updateProductById(1, productDto);

        verify(productRepository).save(product);
        verify(productMapper).toEntity(productDto);
    }

    @Test
    void deleteProductByIdTest() {
        doNothing().when(productRepository).deleteById(anyInt());

        productService.deleteProductById(1);

        verify(productRepository).deleteById(1);
    }

    @Test
    void findProductsByOrderIdTest() {
        List<Product> products = Collections.singletonList(new Product());
        when(productRepository.findByOrderId(anyInt())).thenReturn(products);
        List<ProductDto> productDtos = Collections.singletonList(new ProductDto());
        when(productMapper.toDtoList(anyList())).thenReturn(productDtos);

        List<ProductDto> foundProducts = productService.findProductsByOrderId(1);

        assertFalse(foundProducts.isEmpty());
        verify(productRepository).findByOrderId(1);
    }

}
