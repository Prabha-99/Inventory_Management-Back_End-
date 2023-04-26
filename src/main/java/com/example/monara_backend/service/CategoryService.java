package com.example.monara_backend.service;

import com.example.monara_backend.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {
    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

    public CategoryService(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }
    // Beginning of the Inventory Admin
    //Count category
    public long getCategoryCount(){

        return categoryRepo.count();
    }
    //End of the Inventory Admin
}
