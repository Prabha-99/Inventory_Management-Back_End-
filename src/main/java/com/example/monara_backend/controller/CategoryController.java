package com.example.monara_backend.controller;

import com.example.monara_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("api/v1/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Beginning of the Inventory Admin
    @GetMapping("/categoryCount")
    public long getCategoryCount(){
        return categoryService.getCategoryCount();
    }

    //End of Inventory Admin
}
