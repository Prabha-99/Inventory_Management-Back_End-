package com.example.monara_backend.controller;


import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.service.PdfBillSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/bill")
public class PdfBillSaveController {

    @Autowired
    private PdfBillSaveService pdfFileService;

 

    @GetMapping("/all")
    public ResponseEntity<List<PdfBillSave>> getAllPdf () {
        List<PdfBillSave> pdfList = pdfFileService.getAllPdf();
        return new ResponseEntity<>(pdfList, HttpStatus.OK);
    }


    @GetMapping("/files/{filename}")
    public ResponseEntity<?> downloadImageFromFileSystem (@PathVariable String filename) throws IOException {
        byte[] fileData = pdfFileService.downloadFileFromFileSystem(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/pdf"))
                .body(fileData);
    }

    @PostMapping("/pdf")
    public ResponseEntity<?> uploadFile(@RequestParam("html2pdf-file")MultipartFile file) throws IOException {
        String uploadFile = pdfFileService.uploadFileToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadFile);
    }
}
