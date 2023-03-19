package com.example.monara_backend.service;

import com.example.monara_backend.dto.StockManager_ProductDto;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.StockManager_ProductRepo;
import com.example.monara_backend.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StockManager_ProductService {

    @Autowired
    private StockManager_ProductRepo stockManager_productRepo;

    @Autowired
    private ModelMapper modelMapper;
    public String saveProduct(StockManager_ProductDto stockManager_productDto){
        if(stockManager_productRepo.existsById(stockManager_productDto.getProduct_id())){
            return VarList.RSP_DUPLICATED;
        }else{
            stockManager_productRepo.save(modelMapper.map(stockManager_productDto, Product.class));
            return VarList.RSP_SUCCESS;
        }
    }
}
