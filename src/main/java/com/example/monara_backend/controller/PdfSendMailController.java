package com.example.monara_backend.controller;


import com.example.monara_backend.service.BillSaveService;
import com.example.monara_backend.service.PdfBillSaveService;
import com.example.monara_backend.service.PdfSendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billmail")
public class PdfSendMailController {

    @Autowired
    private PdfSendMailService pdfSendMailService;

  @PostMapping("/send/{bill_id}")
  public ResponseEntity<String> sendBillByEmail( @PathVariable Long bill_id) {
      try {
          pdfSendMailService.sendBillByEmail(bill_id);
          return ResponseEntity.ok("Bill sent successfully");
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send bill: " + e.getMessage());
      }
  }



}
