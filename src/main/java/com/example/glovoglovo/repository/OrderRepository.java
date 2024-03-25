package com.example.glovoglovo.repository;

import com.example.glovoglovo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
