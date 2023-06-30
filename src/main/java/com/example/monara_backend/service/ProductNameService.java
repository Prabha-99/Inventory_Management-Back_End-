package com.example.monara_backend.service;

import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductNameService {

    private static final String DELIMITER = " - ";

    @Autowired
    private ProductRepo productRepo;


    public List<String> getAllProducts() {
        List<Product> names = productRepo.findAll();
        return names.stream().map(Product::getProduct_name).collect(Collectors.toList());
    }

    public Double getProductPrice(String productName) {
        return productRepo.findPriceByProductName(productName);
    }
}
