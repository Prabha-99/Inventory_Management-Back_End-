package com.example.monara_backend.controller;

import com.example.monara_backend.dto.StockManager_ProductDto;
import com.example.monara_backend.service.StockManager_ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
public class StockManager_ProductController {

    @Autowired
    private StockManager_ProductService stockManager_productService;
    @PostMapping("api/v1/products")
    public ResponseEntity saveProduct(@RequestBody StockManager_ProductDto stockManager_productDto){
        try{
            String res = stockManager_productService.saveProduct(stockManager_productDto);
            if (res.equals("00")){

            }else if(res.equals("06")){

            }else{

            }
        }catch (Exception ex){

        }
    }
}
