package com.example.monara_backend.service;

import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.CategoryRepo;
import com.example.monara_backend.repository.ProductRepo;
import com.example.monara_backend.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    //Add products to database
    public Product saveProduct (Product product) {
        return productRepo.save(product);
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

    //Get all products names
    public List<String> getAllProductNames() {
        return productRepo.getAllProductNames();
    }

    //Get all product brands
    public List<String> getAllProductBrands() {
        return productRepo.getAllProductBrands();
    }

    //Count of Product

    public long getProductCount(){
        return productRepo.count();
    }

    public List<String> getProductNamesByCategoryIds() {
        return productRepo.getProductNamesByCategoryIds();
    }

    public List<String> getProductBrandsByCategoryIds() {
        return productRepo.getProductBrandsByCategoryIds();
    }

    public List<String> getNamesByCategoryIds() {
        return productRepo.getNamesByCategoryIds();
    }

    public List<String> getBrandsByCategoryIds() {
        return productRepo.getBrandsByCategoryIds();
    }


    //End of the Inventory Admin

    public void reduceProductQuantity(String product_name, String product_brand, int product_quantity) {
        productRepo.reduceProductQuantity(product_name,product_brand,product_quantity);
    }


    public void increaseProductQuantity(String product_name, String product_brand, int product_quantity) {
        productRepo.increaseProductQuantity(product_name,product_brand,product_quantity);
}



}



