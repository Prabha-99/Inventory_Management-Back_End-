package com.example.monara_backend.service;

import com.example.monara_backend.dto.InventoryAd_ProductDto;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.InventoryAd_CategoryRepo;
import com.example.monara_backend.repository.InventoryAd_ProductRepo;
import com.example.monara_backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InventoryAd_ProductService {

    //Inject
    @Autowired
    private InventoryAd_ProductRepo inventoryAd_productRepo;

    @Autowired
    private InventoryAd_CategoryRepo inventoryAd_categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    //Add products to database
    public String saveProduct(InventoryAd_ProductDto inventoryAd_productDto){
        //Check the data exist or not in the database
        if(inventoryAd_productRepo.existsById(inventoryAd_productDto.getProduct_id())){
            return VarList.RSP_DUPLICATED;
        }else{
            inventoryAd_productRepo.save(modelMapper.map(inventoryAd_productDto, Product.class));
            return VarList.RSP_SUCCESS;
        }

    }

    //Update product in database
    public String updateProduct(InventoryAd_ProductDto inventoryAd_productDto){
        //Check the product exist or not
        if(inventoryAd_productRepo.existsById(inventoryAd_productDto.getProduct_id())){
            inventoryAd_productRepo.save(modelMapper.map(inventoryAd_productDto, Product.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    //Get All products
    public List<InventoryAd_ProductDto> getAllProduct(){
        List<Product> productList = inventoryAd_productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<ArrayList<InventoryAd_ProductDto>>(){
        }.getType());
    }

    // Search products
    public InventoryAd_ProductDto searchProduct(int productId){
        if (inventoryAd_productRepo.existsById(productId)){
            Product product = inventoryAd_productRepo.findById(productId).orElse(null);
            return modelMapper.map(product,  InventoryAd_ProductDto.class);
        }else{
            return null;

        }
    }

    //Delete products
    public String deleteProduct(int productId){
        if (inventoryAd_productRepo.existsById(productId)){
            inventoryAd_productRepo.deleteById(productId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    //Count of Product

    public long getProductCount(){
        return inventoryAd_productRepo.count();
    }
}
