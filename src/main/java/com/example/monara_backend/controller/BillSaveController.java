package com.example.monara_backend.controller;

import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.service.BillSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/billdata")

public class BillSaveController {

    @Autowired
    private BillSaveService billSaveService;

    //save billing info
    @PostMapping("/save")
    public ResponseEntity<BillSave> FormData(@RequestBody BillSave bill) {
        BillSave savedBill = billSaveService.FormData(bill);
        return ResponseEntity.ok(savedBill);
    }


//    //get all bill info
    @GetMapping("/view")
    public List<BillSave> getAllDetails() {
        return billSaveService.getAllDetails();
    }


//    //delete by id bill

    @DeleteMapping("/delete/{bill_id}")
    public ResponseEntity<String> deleteById(@PathVariable Long bill_id) {
        billSaveService.deleteById(bill_id);
        return ResponseEntity.ok().body("Deleted successfully.");
    }

}
