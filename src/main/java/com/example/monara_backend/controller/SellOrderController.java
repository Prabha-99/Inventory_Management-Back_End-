package com.example.monara_backend.controller;


import com.example.monara_backend.dto.PurchaseOrderDto;
import com.example.monara_backend.dto.ResponseDto;
import com.example.monara_backend.dto.SellOrderDto;
import com.example.monara_backend.service.SellOrderService;
import com.example.monara_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/sellOrder")
public class SellOrderController {

    @Autowired
    private SellOrderService sellOrderService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/saveSell")    //Add products
    public ResponseEntity saveSellOrder(@RequestBody SellOrderDto sellOrderDto){
        try{

            String res = sellOrderService.saveSellOrder(sellOrderDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(sellOrderDto);

                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else if(res.equals("06")){
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product Added");
                responseDto.setContent(sellOrderDto);
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
