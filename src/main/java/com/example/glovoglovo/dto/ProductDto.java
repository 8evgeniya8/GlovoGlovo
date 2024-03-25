package com.example.glovoglovo.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    @Id
    private Integer id;
    private String name;
    private double cost;
    private Integer orderId;
}
