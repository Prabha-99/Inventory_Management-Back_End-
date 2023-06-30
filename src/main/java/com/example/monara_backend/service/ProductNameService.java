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
        List<Product> products = productRepo.findAll();
        return products.stream().map(product -> product.getProduct_name() + " - " + product.getProduct_price()).collect(Collectors.toList());
    }
    
    public List<Double> getAllPriceProducts() {
        List<Product> names = productRepo.findAll();
        return names.stream().map(Product::getProduct_price).collect(Collectors.toList());
    }

}
