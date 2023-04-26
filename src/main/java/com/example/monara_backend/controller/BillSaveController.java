package com.example.monara_backend.controller;

import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.service.BillSaveService;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/bill")
public class BillSaveController {
    @Autowired
    private BillSaveService billSaveService;

    @PostMapping("/save")
    public BillSave saveTransaction(@RequestBody BillSave billSave) {
        return billSaveService.saveTransaction(billSave);
    }

}
