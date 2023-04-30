package com.example.monara_backend.controller;


import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfBillRepo;
import com.example.monara_backend.service.PdfBillSaveService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/bill")
public class PdfBillSaveController {

    private final PdfBillSaveService pdfFileService;

    public PdfBillSaveController(PdfBillSaveService pdfFileService) {
        this.pdfFileService = pdfFileService;
    }

    @PostMapping("/pdf")
    public ResponseEntity<PdfBillSave> uploadPdf(@RequestParam("print-section") MultipartFile file) {
        try {
            PdfBillSave pdfFile = pdfFileService.uploadPdf(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(pdfFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/viewpdf", produces = "text/html")
    public ResponseEntity<String> getAllPdfAsHtml() {
        List<PdfBillSave> entities = pdfFileService.getAllEntities();

        StringBuilder htmlBuilder = new StringBuilder();
        for (PdfBillSave entity : entities) {
            byte[] data = entity.getContent();
            String base64Data = Base64.getEncoder().encodeToString(data);
            String htmlElement = "<embed src=\"data:application/pdf;base64," + base64Data + "\" type=\"application/pdf\" width=\"100%\" height=\"600px\">";
            htmlBuilder.append(htmlElement);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        headers.setContentLength(htmlBuilder.length());
        return new ResponseEntity<>(htmlBuilder.toString(), headers, HttpStatus.OK);
    }

}
