package com.example.glovoglovo.mapper;

import com.example.glovoglovo.dto.ProductDto;
import com.example.glovoglovo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Mapping(source = "order.id", target = "orderId")
    ProductDto toDto(Product entity);

    @Mapping(source = "orderId", target = "order.id")
    Product toEntity(ProductDto dto);

    List<ProductDto> toDtoList(List<Product> productList);
}
