package com.example.monara_backend.repository;

import com.example.monara_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    
}
