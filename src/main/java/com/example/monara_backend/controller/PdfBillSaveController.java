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
    public ResponseEntity<PdfBillSave> uploadPdf(@RequestParam("fData") MultipartFile file) {
        try {
            PdfBillSave pdf = pdfFileService.savePdf(file);
            return ResponseEntity.ok(pdf);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


//    @GetMapping(value = "/view", produces = "application/pdf")
//    public ResponseEntity<byte[]> viewAllPdfs() throws IOException {
//        byte[] pdfData = pdfFileService.getAllPdfs();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentLength(pdfData.length);
//        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
//    }

}
