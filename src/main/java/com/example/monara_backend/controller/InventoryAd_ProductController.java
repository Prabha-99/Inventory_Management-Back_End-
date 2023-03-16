package com.example.monara_backend.controller;

import com.example.monara_backend.dto.InventoryAd_ProductDto;
import com.example.monara_backend.dto.InventoryAd_ResponseDto;
import com.example.monara_backend.service.InventoryAd_ProductService;
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
public class InventoryAd_ProductController {

    @Autowired
    private InventoryAd_ProductService inventoryAd_productService;

    @Autowired
    private InventoryAd_ResponseDto inventoryAd_responseDto;

    @PostMapping(value = "/saveProduct")
    public ResponseEntity saveProduct(@RequestBody InventoryAd_ProductDto inventoryAd_productDto){
        try{
            String res = inventoryAd_productService.saveProduct(inventoryAd_productDto);
            if (res.equals("00")){
                inventoryAd_responseDto.setCode(VarList.RSP_SUCCESS);
                inventoryAd_responseDto.setMessage("Success");
                inventoryAd_responseDto.setContent(inventoryAd_productDto);
                return new ResponseEntity(inventoryAd_responseDto, HttpStatus.ACCEPTED);
            }else if(res.equals("06")){
                inventoryAd_responseDto.setCode(VarList.RSP_DUPLICATED);
                inventoryAd_responseDto.setMessage("Product Added");
                inventoryAd_responseDto.setContent(inventoryAd_productDto);
                return new ResponseEntity(inventoryAd_responseDto, HttpStatus.BAD_REQUEST);
            }else{
                inventoryAd_responseDto.setCode(VarList.RSP_FAIL);
                inventoryAd_responseDto.setMessage("Error");
                inventoryAd_responseDto.setContent(null);
                return new ResponseEntity(inventoryAd_responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            inventoryAd_responseDto.setCode(VarList.RSP_ERROR);
            inventoryAd_responseDto.setMessage(ex.getMessage());
            inventoryAd_responseDto.setContent(null);
            return new ResponseEntity(inventoryAd_responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
