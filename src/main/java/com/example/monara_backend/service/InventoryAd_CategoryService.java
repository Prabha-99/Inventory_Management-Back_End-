package com.example.monara_backend.service;

import com.example.monara_backend.repository.InventoryAd_CategoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class InventoryAd_CategoryService {

    @Autowired
    private InventoryAd_CategoryRepo inventoryAd_categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    //Count category
    public long getCategoryCount(){
        return inventoryAd_categoryRepo.count();
    }
}
