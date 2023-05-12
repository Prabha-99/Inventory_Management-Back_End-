package com.example.monara_backend.controller;


import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.service.PdfBillSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/bill")
public class PdfBillSaveController {

    @Autowired
    private PdfBillSaveService pdfFileService;


    @PostMapping("/pdf")
    public ResponseEntity<PdfBillSave> uploadPdf(@RequestParam("jsPDF-file") MultipartFile file) {
        try {
            PdfBillSave pdfFile = pdfFileService.uploadPdf(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(pdfFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/view")
    public List<PdfBillSave> getAllPDFs() {
        return pdfFileService.getAllPDFs();
    }


}
