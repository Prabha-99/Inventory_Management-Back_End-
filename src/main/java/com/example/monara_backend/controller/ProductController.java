package com.example.monara_backend.controller;

import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.dto.ResponseDto;
import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.UserRepo;
import com.example.monara_backend.service.NotificationService;
import com.example.monara_backend.service.ProductService;
import com.example.monara_backend.service.ReportService;
import com.example.monara_backend.util.VarList;
import jakarta.mail.MessagingException;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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

    @Autowired
    private final ReportService reportService;


    public ProductController(NotificationService notificationService, UserRepo userRepo, ReportService reportService) {
        this.notificationService = notificationService;
        this.userRepo = userRepo;
        this.reportService = reportService;
    }

//    List<User> recipients=userRepo.getMails();

    String attachmentPath = "F:/Uni Works/Level 3/Sem 1/Group Project/Reports/GRN.pdf";

    // Get the emails of users to notify



    List<String> recipientEmails = Arrays.asList(      /*This email list should get From the Database not like this*/
            "prabhashana77@gmail.com"
    );


    @PostMapping("/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) throws MessagingException {
        Product saved = productService.saveProduct(product);

        // Send notifications to each user
        for (String recipientEmail : recipientEmails) {
            notificationService.productAddNotification(recipientEmail, product.getProduct_name(),product.getCategory_id(), String.valueOf(product.getProduct_quantity()));
        }
        return ResponseEntity.ok(saved);
    }


    @PutMapping("/{productID}")
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
    public ResponseEntity<String> deleteProduct(@PathVariable Integer product_id) throws MessagingException, JRException, FileNotFoundException {
        productService.deleteProduct(product_id);


        //Generating the Updated Stock Report
        reportService.exportStockReport();

        // Send notifications to each user
        for (String recipientEmail : recipientEmails) {
            notificationService.productDeleteNotification(recipientEmail, "product_id", "product_id");
        }

        return ResponseEntity.ok("Product deleted");
    }

    //Get all product names
    @GetMapping("/names")
    public List<String> getAllProductNames() {
        return productService.getAllProductNames();
    }

    //Get all product brands
    @GetMapping("/BrandNames")
    public List<String> getAllProductBrands() {
        return productService.getAllProductBrands();
    }

    //To get count of product
    @GetMapping("/productCount")
    public long getProductCount(){
        return productService.getProductCount();
    }

    //purchase coordinator
    @GetMapping("/productByCate")
    public List<String> getProductNamesByCategoryIds() {
        return productService.getProductNamesByCategoryIds();
    }

    @GetMapping("/brandByCate")
    public List<String> getProductBrandsByCategoryIds() {
        return productService.getProductBrandsByCategoryIds();
    }

    //stock manager
    @GetMapping("/nameByCate")
    public List<String> getNamesByCategoryIds() {
        return productService.getNamesByCategoryIds();
    }

    @GetMapping("/brandNameByCate")
    public List<String> getBrandsByCategoryIds() {
        return productService.getBrandsByCategoryIds();
    }

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

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer product_id){
        Product product = productService.getProductById(product_id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return  new ResponseEntity<>(product,HttpStatus.OK);
        }
    }
}
