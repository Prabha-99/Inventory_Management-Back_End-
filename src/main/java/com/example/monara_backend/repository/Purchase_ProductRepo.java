package com.example.monara_backend.repository;

import com.example.monara_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Purchase_ProductRepo extends JpaRepository<Product, String> {
}
