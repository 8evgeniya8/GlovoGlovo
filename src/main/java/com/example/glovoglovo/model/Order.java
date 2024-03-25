package com.example.glovoglovo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate date;
    private double cost;


    @OneToMany(cascade = CascadeType.ALL)
    private List <Product> products;
}

