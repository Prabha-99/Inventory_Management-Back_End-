package com.example.monara_backend.controller;

import com.example.monara_backend.dto.Purchase_ProductDto;
import com.example.monara_backend.service.Purchase_ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/user")
@CrossOrigin
public class Purchase_ProductController {
    @Autowired
    private Purchase_ProductService purchase_productService;

    @PostMapping("/saveProduct")
    public Purchase_ProductDto saveProduct(@RequestBody Purchase_ProductDto purchase_productDto){
        return purchase_productService.saveProduct(purchase_productDto);
    }
}
