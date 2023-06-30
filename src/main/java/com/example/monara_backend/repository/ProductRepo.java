package com.example.monara_backend.repository;

import com.example.monara_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Modifying
    @Query("UPDATE Product p SET p.product_quantity = p.product_quantity - :product_quantity WHERE p.product_name = :product_name AND p.product_brand = :product_brand")
    void reduceProductQuantity(String product_name, String product_brand, int product_quantity);

    @Modifying
    @Query("UPDATE Product p SET p.product_quantity = p.product_quantity + :product_quantity WHERE p.product_name = :product_name AND p.product_brand = :product_brand")
    void increaseProductQuantity(String product_name, String product_brand, int product_quantity);
}
