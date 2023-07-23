package com.example.monara_backend.controller;
import com.example.monara_backend.model.DesignerBillSend;

import com.example.monara_backend.model.Product;

import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.repository.ProductRepo;
import com.example.monara_backend.repository.ShowroomRepo;
import com.example.monara_backend.service.DesignerBillSendService;
import com.example.monara_backend.service.ProductService;
import com.example.monara_backend.service.ShowroomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/designer")


public class DesignerController {

    @Autowired
    private ShowroomService showroomService;

    @Autowired
    private DesignerBillSendService designerBillSendService;


    @Autowired
    private ProductService productService;

    @Autowired
    private ShowroomRepo showroomRepo;






    @GetMapping("/files")

    public List<ShowroomFile> getAllFiles() {
        return showroomService.getAllFiles();
    }

    public class JacksonConfig {

        @Bean
        public ObjectMapper objectMapper() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            return objectMapper;
        }
    }


    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam Integer id) {
        ShowroomFile file = showroomService.getFileById(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        // Get the file path from the ShowroomFile entity
        String filePath = file.getFilePath();
        File downloadFile = new File(filePath);

        if (!downloadFile.exists() || !downloadFile.isFile()) {
            return ResponseEntity.notFound().build();
        }

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFilename());

        // Create an InputStreamResource from the file path
        InputStreamResource inputStreamResource;
        try {
            inputStreamResource = new InputStreamResource(new FileInputStream(downloadFile));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        // Stream the file content to the response
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(downloadFile.length())
                .body(inputStreamResource);
    }

    @PostMapping("/billSend")

    public ResponseEntity<String> addFile(@RequestParam("file") MultipartFile file,
                                          @RequestParam("customerName") String customerName) throws IOException {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>("Please select a file.", HttpStatus.BAD_REQUEST);
        }

        String fileName = file.getOriginalFilename();
        String fileStoragePath = "C:\\Users\\hp\\Desktop\\designer bill\\"; // Replace this with the actual storage path

        // Save the file to the file system
        File savedFile = new File(fileStoragePath + fileName);
        file.transferTo(savedFile);

        DesignerBillSend fileUpload = new DesignerBillSend();
        fileUpload.setFileName(fileName);
        fileUpload.setFilePath(savedFile.getAbsolutePath());
        fileUpload.setCustomerName(customerName);

        designerBillSendService.saveBill(fileUpload);
        return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
    }

    public ResponseEntity<String> uploadBill(HttpServletRequest request,
                                             @RequestParam("filename") String filename,
                                             @RequestParam("customerName") String customerName,
                                             @RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Blob blob = new SerialBlob(bytes);

            DesignerBillSend fileUpload = new DesignerBillSend();
            fileUpload.setFileName(file.getOriginalFilename());
            fileUpload.setCustomerName(customerName);
            fileUpload.setDbFile(blob);
            // Save the DesignerBillSend entity to the database using your service
            designerBillSendService.saveBill(fileUpload);

            // You can add any additional logic here if needed

            return ResponseEntity.ok("Bill uploaded successfully.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading bill.");
        }
    }

   /* public String addFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException, SerialException, SQLException {
        byte[] bytes = file.getBytes();

        Blob blob = new SerialBlob(bytes);

        DesignerBillSend fileUpload = new DesignerBillSend();
        fileUpload.setFileName(file.getOriginalFilename());
        fileUpload.setDbFile(blob);
        designerBillSendService.saveBill(fileUpload);
        return "redirect:/";


    public ResponseEntity<String> uploadBill(@RequestParam("filename") String filename,
                                             @RequestParam("customerName") String customerName,
                                             @RequestPart("file") MultipartFile file) {
        // Your logic to process the uploaded file and customer name
        // You can access them as: filename, customerName, and file

        return ResponseEntity.ok("Bill uploaded successfully.");*/



}
