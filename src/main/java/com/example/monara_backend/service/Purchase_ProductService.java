package com.example.monara_backend.service;

import com.example.monara_backend.dto.Purchase_ProductDto;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.Purchase_ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class Purchase_ProductService {
    @Autowired
    private Purchase_ProductRepo purchase_productRepo;

    @Autowired
    private ModelMapper modelMapper;
    public  Purchase_ProductDto saveProduct(Purchase_ProductDto purchase_productDto){

        purchase_productRepo.save(modelMapper.map(purchase_productDto, Product.class));
        return purchase_productDto;

    }
    public List<Purchase_ProductDto> getAllProducts(){

        List<Product>productList=purchase_productRepo.findAll();
        return modelMapper.map(productList,new TypeToken<List<Purchase_ProductDto>>(){}.getType());
    }
}
