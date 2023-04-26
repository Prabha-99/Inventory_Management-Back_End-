package com.example.monara_backend.repository;

import com.example.monara_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,String> {

}
