package com.example.monara_backend.service;

import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.CategoryRepo;
import com.example.monara_backend.repository.ProductRepo;
import com.example.monara_backend.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {


    //Inject
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Beginning of the Inventory Admin
    //Add products to database
    public String saveProduct(ProductDto productDto){
        //Check the data exist or not in the database
        if(productRepo.existsById(productDto.getProduct_id())){
            return VarList.RSP_DUPLICATED;
        }else{
            productRepo.save(modelMapper.map(productDto, Product.class));
            return VarList.RSP_SUCCESS;
        }

    }

    //Update product in database
    public Product updateProduct(Integer productID, Product product) {
        Product existingProduct = productRepo.findById(productID).orElse(null);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setCategory_id(product.getCategory_id());
        existingProduct.setProduct_brand(product.getProduct_brand());
        existingProduct.setProduct_name(product.getProduct_name());
        existingProduct.setProduct_price(product.getProduct_price());
        existingProduct.setProduct_quantity(product.getProduct_quantity());
        return productRepo.save(existingProduct);
    }


    public List<Product> getAllProduct () {
        return productRepo.findAll();
    }

    // Search products
    public ProductDto searchProduct(int productId){
        if (productRepo.existsById(productId)){
            Product product = productRepo.findById(productId).orElse(null);
            return modelMapper.map(product, ProductDto.class);
        }else{
            return null;

        }
    }

    //Delete products
    public String deleteProduct(int productId){
        if (productRepo.existsById(productId)){
            productRepo.deleteById(productId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    //Count of Product

    public long getProductCount(){
        return productRepo.count();
    }

    //End of the Inventory Admin

    public void reduceProductQuantity(String product_name, String product_brand, int product_quantity) {
        productRepo.reduceProductQuantity(product_name,product_brand,product_quantity);
    }


    public void increaseProductQuantity(String product_name, String product_brand, int product_quantity) {
        productRepo.increaseProductQuantity(product_name,product_brand,product_quantity);
}

    //designer deduct product
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product getProductById(Integer productID) {
        Optional<Product> optionalProduct = productRepo.findById(productID);
        return optionalProduct.orElse(null);
    }

    public void deductProduct(Integer productID, Product updatedProduct) {
        // Retrieve the existing product from the database
        Product existingProduct = getProductById(productID);

        if (existingProduct != null) {
            // Update the necessary fields of the existing product
            existingProduct.setProduct_name(updatedProduct.getProduct_name());
            existingProduct.setProduct_quantity(updatedProduct.getProduct_quantity());
            // Update other fields as needed

            // Save the updated product in the database
            productRepo.save(existingProduct);
        }
    }

}
