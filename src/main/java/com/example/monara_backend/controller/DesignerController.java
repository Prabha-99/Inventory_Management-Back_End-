package com.example.monara_backend.controller;
import com.example.monara_backend.model.DesignerBillSend;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.service.DesignerBillSendService;
import com.example.monara_backend.service.ProductService;
import com.example.monara_backend.service.ShowroomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/designer")
@Configuration


public class DesignerController {
    @Autowired
    private ShowroomService showroomService;

    @Autowired
    private DesignerBillSendService designerBillSendService;

    @Autowired
    private ProductService productService;

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

    @PostMapping("/billSend")
    public String addFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException, SerialException, SQLException
    {
        byte[] bytes = file.getBytes();

        Blob blob = new SerialBlob(bytes);

        DesignerBillSend fileUpload =new DesignerBillSend();
        fileUpload.setFileName(file.getOriginalFilename());
        fileUpload.setDbFile(blob);
        designerBillSendService.saveBill(fileUpload);
        return "redirect:/";

    }

    /*@PutMapping("/deductProduct")
    public ResponseEntity<Product> deductProduct(@PathVariable Integer productID, @RequestBody Product product) {
        // Retrieve the existing product from the database
        Product existingProduct = productService.getProductById(productID);

        // Check if the product exists
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Deduct the quantity from the existing product
        int quantityToDeduct = product.getProduct_quantity();
        int existingQuantity = existingProduct.getProduct_quantity();
        if (existingQuantity < quantityToDeduct) {
            // If the quantity to deduct is greater than the existing quantity, return an error response
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        existingProduct.setProduct_quantity(existingQuantity - quantityToDeduct);

        // Update the product in the database
        productService.updateProduct(productID, existingProduct);

        // Return the updated product with HTTP status OK
        return new ResponseEntity<>(existingProduct, HttpStatus.OK);
    }*/
    @PutMapping("/deductProduct")
    public ResponseEntity<Product> deductProduct(@RequestBody Product product) {
        // Retrieve the existing product from the database using the ID from the request body
        Integer productID = product.getProduct_id();
        Product existingProduct = productService.getProductById(productID);

        // Check if the product exists
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Deduct the quantity from the existing product
        int quantityToDeduct = product.getProduct_quantity();
        int existingQuantity = existingProduct.getProduct_quantity();
        if (existingQuantity < quantityToDeduct) {
            // If the quantity to deduct is greater than the existing quantity, return an error response
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        existingProduct.setProduct_quantity(existingQuantity - quantityToDeduct);

        // Update the product in the database
        productService.updateProduct(productID, existingProduct);

        // Return the updated product with HTTP status OK
        return new ResponseEntity<>(existingProduct, HttpStatus.OK);
    }

}
