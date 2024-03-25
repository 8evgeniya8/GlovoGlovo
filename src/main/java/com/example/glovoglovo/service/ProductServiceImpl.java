package com.example.glovoglovo.service;

import com.example.glovoglovo.dto.ProductDto;
import com.example.glovoglovo.mapper.ProductMapper;
import com.example.glovoglovo.model.Order;
import com.example.glovoglovo.model.Product;
import com.example.glovoglovo.repository.OrderRepository;
import com.example.glovoglovo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto findProductById(int id) {
        Optional<Product> optionalOfProduct = productRepository.findById(id);
        Product product = optionalOfProduct.orElse(null);
        if(product == null) log.error("There is no product");
        return productMapper.toDto(product);
    }

    public List<ProductDto> findAllProducts() {
        List <Product> productList = (List<Product>) productRepository.findAll();
        return productMapper.toDtoList(productList);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        if (null != productDto.getOrderId()) {
            Order order = orderRepository.findById(productDto.getOrderId())
                    .orElseThrow(() -> new EntityNotFoundException("Order not found"));
            product.setOrder(order);
        }
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void updateProductById(int id, ProductDto productDto) {
        if (productDto == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        Product product = productMapper.toEntity(productDto);
        product.setId(id);
        productRepository.save(product);
        log.debug("Updated product: " + product.getName());
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
        log.trace("Deleted product : " + id);
    }

    @Override
    public List<ProductDto> findProductsByOrderId(int id) {
        List<Product> products = productRepository.findByOrderId(id);
        return productMapper.toDtoList(products);
    }
}
