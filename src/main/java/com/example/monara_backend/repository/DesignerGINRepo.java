package com.example.monara_backend.repository;

import com.example.monara_backend.model.DesignerGIN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerGINRepo extends JpaRepository<DesignerGIN , Integer> {
}
