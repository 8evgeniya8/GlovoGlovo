package com.example.glovoglovo.controller;

import com.example.glovoglovo.dto.ProductDto;
import com.example.glovoglovo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable int id) {
        ProductDto productDto = productService.findProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        List<ProductDto> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductById(@PathVariable int id, @RequestBody ProductDto productDto) {
        productService.updateProductById(id, productDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<List<ProductDto>> findProductsByOrderId(@PathVariable int orderId) {
        List<ProductDto> products = productService.findProductsByOrderId(orderId);
        return ResponseEntity.ok(products);
    }
}

