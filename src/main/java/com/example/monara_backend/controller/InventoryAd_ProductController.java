package com.example.monara_backend.controller;

import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.dto.ResponseDto;
import com.example.monara_backend.service.InventoryAd_ProductService;
import com.example.monara_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/product")

public class InventoryAd_ProductController {

    @Autowired
    private InventoryAd_ProductService inventoryAd_productService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/saveProduct")    //Add products
    public ResponseEntity saveProduct(@RequestBody ProductDto productDto){
        try{
            String res = inventoryAd_productService.saveProduct(productDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(productDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else if(res.equals("06")){
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product Added");
                responseDto.setContent(productDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else{
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateProduct") //Update products
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto){

        try{
            String res = inventoryAd_productService.updateProduct(productDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(productDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else if(res.equals("01")){
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product is not exist");
                responseDto.setContent(productDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllProduct") //Get all products from database
    public ResponseEntity geAllProduct(){
        try{
            List<ProductDto> productDtoList = inventoryAd_productService.getAllProduct();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Success");
            responseDto.setContent(productDtoList);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchProduct/{productID}") //Get searched product
    public ResponseEntity searchProduct(@PathVariable int productID){
        try{
            ProductDto productDto = inventoryAd_productService.searchProduct(productID);
            if (productDto != null){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(productDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product not Available");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteProduct/{productID}") //delete a product
    public ResponseEntity deleteProduct(@PathVariable int productID){
        try{
            String res = inventoryAd_productService.deleteProduct(productID);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Successfully Deleted");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product not Available");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //To get count of product

    @GetMapping("/productCount")
    public long getProductCount(){
        return inventoryAd_productService.getProductCount();
    }
}
