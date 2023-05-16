package com.example.monara_backend.controller;


import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.service.PdfBillSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/bill")
public class PdfBillSaveController {

    @Autowired
    private PdfBillSaveService pdfFileService;
    
    @PostMapping("/pdf")
    public ResponseEntity<PdfBillSave> uploadPdf(@RequestParam("html2pdf-file") MultipartFile file) {
        try {
            PdfBillSave pdfFile = pdfFileService.uploadPdf(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(pdfFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping(value = "/view", produces = "application/pdf")
    public ResponseEntity<byte[]> viewAllPdfs() throws IOException {
        byte[] pdfData = pdfFileService.getAllPdfs();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

}
