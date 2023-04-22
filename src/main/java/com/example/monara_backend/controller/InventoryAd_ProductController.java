package com.example.monara_backend.controller;

import com.example.monara_backend.dto.InventoryAd_ProductDto;
import com.example.monara_backend.dto.InventoryAd_ResponseDto;
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
    private InventoryAd_ResponseDto inventoryAd_responseDto;

    @PostMapping(value = "/saveProduct")    //Add products
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

    @PutMapping(value = "/updateProduct") //Update products
    public ResponseEntity updateProduct(@RequestBody InventoryAd_ProductDto inventoryAd_productDto){

        try{
            String res = inventoryAd_productService.updateProduct(inventoryAd_productDto);
            if (res.equals("00")){
                inventoryAd_responseDto.setCode(VarList.RSP_SUCCESS);
                inventoryAd_responseDto.setMessage("Success");
                inventoryAd_responseDto.setContent(inventoryAd_productDto);
                return new ResponseEntity(inventoryAd_responseDto, HttpStatus.ACCEPTED);

            }else if(res.equals("01")){
                inventoryAd_responseDto.setCode(VarList.RSP_DUPLICATED);
                inventoryAd_responseDto.setMessage("Product is not exist");
                inventoryAd_responseDto.setContent(inventoryAd_productDto);
                return new ResponseEntity(inventoryAd_responseDto, HttpStatus.BAD_REQUEST);
            }else {
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

    @GetMapping("/getAllProduct") //Get all products from database
    public ResponseEntity geAllProduct(){
        try{
            List<InventoryAd_ProductDto> productDtoList = inventoryAd_productService.getAllProduct();
            inventoryAd_responseDto.setCode(VarList.RSP_SUCCESS);
            inventoryAd_responseDto.setMessage("Success");
            inventoryAd_responseDto.setContent(productDtoList);
            return new ResponseEntity(inventoryAd_responseDto, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            inventoryAd_responseDto.setCode(VarList.RSP_ERROR);
            inventoryAd_responseDto.setMessage(ex.getMessage());
            inventoryAd_responseDto.setContent(null);
            return new ResponseEntity(inventoryAd_responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchProduct/{productID}") //Get searched product
    public ResponseEntity searchProduct(@PathVariable int productID){
        try{
            InventoryAd_ProductDto inventoryAd_productDto = inventoryAd_productService.searchProduct(productID);
            if (inventoryAd_productDto != null){
                inventoryAd_responseDto.setCode(VarList.RSP_SUCCESS);
                inventoryAd_responseDto.setMessage("Success");
                inventoryAd_responseDto.setContent(inventoryAd_productDto);
                return new ResponseEntity(inventoryAd_responseDto, HttpStatus.ACCEPTED);
            }else {
                inventoryAd_responseDto.setCode(VarList.RSP_DUPLICATED);
                inventoryAd_responseDto.setMessage("Product not Available");
                inventoryAd_responseDto.setContent(null);
                return new ResponseEntity(inventoryAd_responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            inventoryAd_responseDto.setCode(VarList.RSP_ERROR);
            inventoryAd_responseDto.setMessage(ex.getMessage());
            inventoryAd_responseDto.setContent(ex);
            return new ResponseEntity(inventoryAd_responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteProduct/{productID}") //delete a product
    public ResponseEntity deleteProduct(@PathVariable int productID){
        try{
            String res = inventoryAd_productService.deleteProduct(productID);
            if (res.equals("00")){
                inventoryAd_responseDto.setCode(VarList.RSP_SUCCESS);
                inventoryAd_responseDto.setMessage("Successfully Deleted");
                inventoryAd_responseDto.setContent(null);
                return new ResponseEntity(inventoryAd_responseDto, HttpStatus.ACCEPTED);
            }else {
                inventoryAd_responseDto.setCode(VarList.RSP_DUPLICATED);
                inventoryAd_responseDto.setMessage("Product not Available");
                inventoryAd_responseDto.setContent(null);
                return new ResponseEntity(inventoryAd_responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            inventoryAd_responseDto.setCode(VarList.RSP_ERROR);
            inventoryAd_responseDto.setMessage(ex.getMessage());
            inventoryAd_responseDto.setContent(ex);
            return new ResponseEntity(inventoryAd_responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //To get count of product

    @GetMapping("/productCount")
    public long getProductCount(){
        return inventoryAd_productService.getProductCount();
    }

}
