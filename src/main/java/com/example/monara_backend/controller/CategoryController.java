package com.example.monara_backend.controller;

import com.example.monara_backend.dto.CategoryDto;
import com.example.monara_backend.dto.ProductDto;
import com.example.monara_backend.dto.ResponseDto;
import com.example.monara_backend.model.Category;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.CategoryRepo;
import com.example.monara_backend.service.CategoryService;
import com.example.monara_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ResponseDto responseDto;



    // Beginning of the Inventory Admin
    @GetMapping("/categoryCount")
    public long getCategoryCount(){
        return categoryService.getCategoryCount();
    }

    @GetMapping("/searchCategory/{categoryID}") //Get searched product
    public ResponseEntity searchCategory(@PathVariable int categoryID){
        try{
            CategoryDto categoryDto = categoryService.searchCategory(categoryID);
            if (categoryDto != null){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(categoryDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product not Available");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //End of Inventory Admin

    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }
}
