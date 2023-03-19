package com.example.monara_backend.repository;

import com.example.monara_backend.model.Category;
import com.example.monara_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockManager_ProductRepo extends JpaRepository<Product, Integer> {

}
