package com.example.monara_backend.repository;

import com.example.monara_backend.model.PdfBillSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfBillRepo extends JpaRepository<PdfBillSave,Long> {

}
