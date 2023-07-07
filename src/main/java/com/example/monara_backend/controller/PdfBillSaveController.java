package com.example.monara_backend.controller;


import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.service.PdfBillSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/bill")
public class PdfBillSaveController {

    @Autowired
    private PdfBillSaveService pdfFileService;

    @PostMapping("/pdf")
    public ResponseEntity<PdfBillSave> uploadPdf(@RequestParam("html2pdf-file") MultipartFile file) {
        try {
            PdfBillSave pdf = pdfFileService.savePdf(file);
            return ResponseEntity.ok(pdf);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("files/{filename:.+}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String filename) throws IOException {
        return pdfFileService.getPdf(filename);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PdfBillSave>> getAllPdf() {
        List<PdfBillSave> pdfList = pdfFileService.getAllPdf();
        return new ResponseEntity<>(pdfList, HttpStatus.OK);
    }

//    @DeleteMapping("delete/{bill_id}")
//    public ResponseEntity<String> deletePdf(@PathVariable Long bill_id) {
//        pdfFileService.deleteBillPdf(bill_id);
//        return ResponseEntity.ok("Bill deleted");
//    }
}
