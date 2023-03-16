package com.example.monara_backend.service;

import com.example.monara_backend.dto.InventoryAd_ProductDto;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.InventoryAd_ProductRepo;
import com.example.monara_backend.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InventoryAd_ProductService {

    //Inject
    @Autowired
    private InventoryAd_ProductRepo inventoryAd_productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveProduct(InventoryAd_ProductDto inventoryAd_productDto){
        //Check the data exist or not in the database
        if(inventoryAd_productRepo.existsById(inventoryAd_productDto.getProduct_id())){
            return VarList.RSP_DUPLICATED;
        }else{
            inventoryAd_productRepo.save(modelMapper.map(inventoryAd_productDto, Product.class));
            return VarList.RSP_SUCCESS;
        }

    }
}
