package com.example.monara_backend.service;

import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.model.Product;
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
    public String updateProduct(ProductDto productDto){
        //Check the product exist or not
        if(productRepo.existsById(productDto.getProduct_id())){
            productRepo.save(modelMapper.map(productDto, Product.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    //Get All products
    public List<ProductDto> getAllProduct(){
        List<Product> productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<ArrayList<ProductDto>>(){
        }.getType());
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
}
