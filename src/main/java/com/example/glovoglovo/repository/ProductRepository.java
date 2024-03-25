package com.example.glovoglovo.repository;


import com.example.glovoglovo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.order.id = :orderId")
        List<Product> findByOrderId(int orderId);
}
