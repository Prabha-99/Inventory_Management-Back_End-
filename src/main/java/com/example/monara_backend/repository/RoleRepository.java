package com.example.monara_backend.repository;

import com.example.monara_backend.model.ERole;
import com.example.monara_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role , Integer> {
    Optional<Role> findByName(ERole name);
}
