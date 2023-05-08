package com.example.monara_backend.controller;

import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.service.BillSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/billdata")
public class BillSaveController {
    @Autowired
    private BillSaveService billSaveService;


    @PostMapping("/save")
    public ResponseEntity<BillSave> FormData(@RequestBody BillSave bill) {
       BillSave savedBill = billSaveService.FormData(bill);
        return ResponseEntity.ok(savedBill);
    }

}
