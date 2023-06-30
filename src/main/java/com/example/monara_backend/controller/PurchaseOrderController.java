package com.example.monara_backend.controller;

import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.dto.PurchaseOrderDto;
import com.example.monara_backend.dto.ResponseDto;
import com.example.monara_backend.repository.PurchaseOrderRepo;
import com.example.monara_backend.service.PurchaseOrderService;
import com.example.monara_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/purchaseOrder")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/savePurchase")    //Add products
    public ResponseEntity savePurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto){
        try{

            String res = purchaseOrderService.savePurchaseOrder(purchaseOrderDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(purchaseOrderDto);

                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else if(res.equals("06")){
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product Added");
                responseDto.setContent(purchaseOrderDto);
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
}
