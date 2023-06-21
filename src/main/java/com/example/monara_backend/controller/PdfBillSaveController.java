package com.example.monara_backend.controller;


import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.service.PdfBillSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


//    @GetMapping("file/{filepath}")
//    public ResponseEntity<byte[]> getPdfFileById(@PathVariable Integer bill_id) {
//        try {
//            byte[] pdfBytes = pdfFileService.getPdfFile(bill_id);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType("application/pdf"));
//            headers.setContentDispositionFormData("inline", "file.pdf");
//            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//            ResponseEntity<byte[]> response = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
//            return response;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    @GetMapping(value = "/file/{filepath:.+}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdfFileByPath(@PathVariable String filepath) {
        try {
            byte[] pdfBytes = pdfFileService.getPdfFileByPath(filepath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline").build());
            headers.setContentLength(pdfBytes.length);
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/all")
    public ResponseEntity<List<PdfBillSave>> getAllPdf() {
        List<PdfBillSave> pdfList = pdfFileService.getAllPdf();
        return new ResponseEntity<>(pdfList, HttpStatus.OK);
    }

    @DeleteMapping("delete/{bill_id}")
    public ResponseEntity<String> deletePdf(@PathVariable Integer bill_id) {
        pdfFileService.deleteBillPdf(bill_id);
        return ResponseEntity.ok("Bill deleted");
    }


}
