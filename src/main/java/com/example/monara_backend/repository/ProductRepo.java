package com.example.monara_backend.repository;

import com.example.monara_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query("SELECT p.product_price FROM Product p WHERE p.product_name = :productName")
    Double findPriceByProductName(@Param("productName") String productName);
}
