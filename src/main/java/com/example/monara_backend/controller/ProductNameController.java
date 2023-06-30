package com.example.monara_backend.controller;

import com.example.monara_backend.model.Product;
import com.example.monara_backend.service.ProductNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/bill")
public class ProductNameController {
    @Autowired
    private ProductNameService productNameService;

    @GetMapping("/getnames")
    public List<String> getAllProducts() {
        return productNameService.getAllProducts();
    }


    @GetMapping("/getprice")
    public List<Double> getAllPriceProducts() {
        return productNameService.getAllPriceProducts();
    }

}
