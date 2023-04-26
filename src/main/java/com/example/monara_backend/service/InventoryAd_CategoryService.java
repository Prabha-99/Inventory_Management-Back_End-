package com.example.monara_backend.service;

import com.example.monara_backend.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InventoryAd_CategoryService {
    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

    public InventoryAd_CategoryService(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    //Count category
    public long getCategoryCount(){

        return categoryRepo.count();
    }
}
