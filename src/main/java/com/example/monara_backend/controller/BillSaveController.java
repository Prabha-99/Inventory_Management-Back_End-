package com.example.monara_backend.controller;

import com.example.monara_backend.service.BillSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/bill")
public class BillSaveController {
    @Autowired
    private BillSaveService billSaveService;

    @PostMapping("/save")
    public ResponseEntity<String> savePdf(@RequestParam("pdfData") MultipartFile pdf_data) {
        try {
            billSaveService.savePdf(pdf_data.getBytes());
            return new ResponseEntity<>("PDF saved to database", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error saving PDF to database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
