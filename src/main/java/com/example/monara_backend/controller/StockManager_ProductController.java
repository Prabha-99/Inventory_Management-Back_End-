package com.example.monara_backend.controller;

import com.example.monara_backend.dto.StockManager_ProductDto;
import com.example.monara_backend.dto.StockManager_ResponseDto;
import com.example.monara_backend.service.StockManager_ProductService;
import com.example.monara_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public class StockManager_ProductController {

    @Autowired
    private StockManager_ProductService stockManager_productService;

    @Autowired
    private StockManager_ResponseDto stockManager_responseDto;
    @PostMapping("saveProduct")//Add product
    public ResponseEntity saveProduct(@RequestBody StockManager_ProductDto stockManager_productDto){
        try{
            String res = stockManager_productService.saveProduct(stockManager_productDto);
            if (res.equals("00")){
                stockManager_responseDto.setCode(VarList.RSP_SUCCESS);
                stockManager_responseDto.setMessage("Success");
                stockManager_responseDto.setContent(stockManager_productDto);
                return new ResponseEntity(stockManager_responseDto, HttpStatus.ACCEPTED);
            }else if(res.equals("06")){
                stockManager_responseDto.setCode(VarList.RSP_DUPLICATED);
                stockManager_responseDto.setMessage("Product Added");
                stockManager_responseDto.setContent(stockManager_productDto);
                return new ResponseEntity(stockManager_responseDto, HttpStatus.BAD_REQUEST);

            }else{
                stockManager_responseDto.setCode(VarList.RSP_FAIL);
                stockManager_responseDto.setMessage("Error");
                stockManager_responseDto.setContent(null);
                return new ResponseEntity(stockManager_responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            stockManager_responseDto.setCode(VarList.RSP_ERROR);
            stockManager_responseDto.setMessage(ex.getMessage());
            stockManager_responseDto.setContent(null);
            return new ResponseEntity(stockManager_responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
