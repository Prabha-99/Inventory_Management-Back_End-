package com.example.monara_backend.repository;

import com.example.monara_backend.model.BillSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillSaveRepo extends JpaRepository<BillSave,Long> {
}
