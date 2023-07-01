package com.example.monara_backend.controller;

import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.dto.ResponseDto;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.UserRepo;
import com.example.monara_backend.service.NotificationService;
import com.example.monara_backend.service.ProductService;
import com.example.monara_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseDto responseDto;

    @Autowired
    private final NotificationService notificationService;

    @Autowired
    private final UserRepo userRepo;

    public ProductController(NotificationService notificationService, UserRepo userRepo) {
        this.notificationService = notificationService;
        this.userRepo = userRepo;
    }

//    List<User> recipients=userRepo.getMails();

    String attachmentPath = "F:/Uni Works/Level 3/Sem 1/Group Project/Reports/GRN.pdf";

    // Get the emails of users to notify

    List<String> recipientEmails = Arrays.asList(
            "prabhashana77@gmail.com",
            "mprabhashmilindu@gmail.com"
    List<String> recipientEmails = Arrays.asList(      /*This email list should get From the Database not like this*/
            "prabhashana77@gmail.com"

    );

    // Beginning of the Inventory Admin
    @PostMapping(value = "/saveProduct")    //Add products
    public ResponseEntity saveProduct(@RequestBody ProductDto productDto){
        try{

            String res = productService.saveProduct(productDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(productDto);

                // Send notifications to each user
                for (String recipientEmail : recipientEmails) {
//                    notificationService.productAddNotification(recipientEmail, productDto.getProduct_name(),productDto.getCategory_id(), String.valueOf(productDto.getProduct_quantity()));
                    notificationService.GRNNotification(recipientEmail,productDto.getProduct_name(),productDto.getCategory_id(),String.valueOf(productDto.getProduct_quantity()),attachmentPath);
                }

                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else if(res.equals("06")){
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product Added");
                responseDto.setContent(productDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }else{
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateProduct/{productID}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productID , @RequestBody Product product){
        return new ResponseEntity<Product>(productService.updateProduct(productID , product) , HttpStatus.OK);
    }

    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/searchProduct/{productID}") //Get searched product
    public ResponseEntity searchProduct(@PathVariable int productID){
        try{
            ProductDto productDto = productService.searchProduct(productID);
            if (productDto != null){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(productDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product not Available");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteProduct/{product_id}") //delete a product
    public ResponseEntity<String> deleteProduct(@PathVariable Integer product_id) {
        productService.deleteProduct(product_id);
        return ResponseEntity.ok("Product deleted");
    }

    //To get count of product
    @GetMapping("/productCount")
    public long getProductCount(){
        return productService.getProductCount();
    }

    //End of Inventory Admin

    @PostMapping("/reduce")
    public void reduceProductQuantity(@RequestBody ProductReduceRequest request) {
        productService.reduceProductQuantity(request.getProduct_name(), request.getProduct_brand(), request.getProduct_quantity());
    }

    public static class ProductReduceRequest {
        private String product_name;
        private String product_brand;
        private int product_quantity;

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_brand() {
            return product_brand;
        }

        public void setProduct_brand(String product_brand) {
            this.product_brand = product_brand;
        }

        public int getProduct_quantity() {
            return product_quantity;
        }

        public void setProduct_quantity(int product_quantity) {
            this.product_quantity = product_quantity;
        }


    }

    @PostMapping("/increase")
    public void increaseProductQuantity(@RequestBody ProductIncreaseRequest request) {
        productService.increaseProductQuantity(request.getProduct_name(), request.getProduct_brand(), request.getProduct_quantity());
    }

    public static class ProductIncreaseRequest {

        private String product_name;
        private String product_brand;
        private int product_quantity;

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_brand() {
            return product_brand;
        }

        public void setProduct_brand(String product_brand) {
            this.product_brand = product_brand;
        }

        public int getProduct_quantity() {
            return product_quantity;
        }

        public void setProduct_quantity(int product_quantity) {
            this.product_quantity = product_quantity;
        }
    }
}
