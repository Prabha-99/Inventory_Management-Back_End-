package com.example.monara_backend.repository;

import com.example.monara_backend.model.PdfBillSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PdfBillRepo extends JpaRepository<PdfBillSave,Integer> {
    List<PdfBillSave> findAll();
}
