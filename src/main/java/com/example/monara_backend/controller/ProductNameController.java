package com.example.monara_backend.controller;

import com.example.monara_backend.service.ProductNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
