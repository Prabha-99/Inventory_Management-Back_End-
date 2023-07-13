package com.example.monara_backend.service;

import com.example.monara_backend.dto.CategoryDto;
import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.model.Category;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CategoryDto searchCategory(int categoryId){
        if (categoryRepo.existsById(categoryId)){
            Category category = categoryRepo.findById(categoryId).orElse(null);
            return modelMapper.map(category, CategoryDto.class);
        }else{
            return null;

        }
    }
    //End of the Inventory Admin

    public List<Category> getAllCategory () {
        return categoryRepo.findAll();
    }

}
