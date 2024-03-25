package com.example.glovoglovo.mapper;

import com.example.glovoglovo.dto.OrderDto;
import com.example.glovoglovo.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    @Mapping(source = "id", target = "id")
    OrderDto toDto(Order order);
    @Mapping(source = "id", target = "id")
    Order toEntity(OrderDto dto);

    List<OrderDto> toDtoList(List<Order> orders);
}
