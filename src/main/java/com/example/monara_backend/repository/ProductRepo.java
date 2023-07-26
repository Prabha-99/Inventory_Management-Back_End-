package com.example.monara_backend.repository;

import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.Report;
import com.example.monara_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Modifying
    @Query("UPDATE Product p SET p.product_quantity = p.product_quantity - :product_quantity WHERE p.product_name = :product_name AND p.product_brand = :product_brand")
    void reduceProductQuantity(String product_name, String product_brand, int product_quantity);

    @Modifying
    @Query("UPDATE Product p SET p.product_quantity = p.product_quantity + :product_quantity WHERE p.product_name = :product_name AND p.product_brand = :product_brand")
    void increaseProductQuantity(String product_name, String product_brand, int product_quantity);

    @Query("SELECT DISTINCT p.product_name FROM Product p")
    List<String> getAllProductNames();

    @Query("SELECT DISTINCT p.product_brand FROM Product p")
    List<String> getAllProductBrands();

    @Query("SELECT DISTINCT p.product_name FROM Product p WHERE p.category.category_id IN ('cat_accessories', 'cat_appliances')")
    List<String> getProductNamesByCategoryIds();

    @Query("SELECT DISTINCT p.product_brand FROM Product p WHERE p.category.category_id IN ('cat_accessories', 'cat_appliances')")
    List<String> getProductBrandsByCategoryIds();

    @Query("SELECT DISTINCT p.product_brand FROM Product p WHERE p.category.category_id IN ('cat_edge', 'cat_melamine')")
    List<String> getBrandsByCategoryIds();

    @Query("SELECT DISTINCT p.product_name FROM Product p WHERE p.category.category_id IN ('cat_edge', 'cat_melamine')")
    List<String> getNamesByCategoryIds();


    @Query("SELECT pd FROM Product pd WHERE pd.product_id = :product_id")
    Product getProductByIds(@Param("product_id") Integer product_id);

}

