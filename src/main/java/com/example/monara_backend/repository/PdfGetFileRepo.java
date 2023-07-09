package com.example.monara_backend.repository;

import com.example.monara_backend.model.PdfBillSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PdfGetFileRepo extends JpaRepository<PdfBillSave,Long> {

    @Query("SELECT p FROM PdfBillSave p WHERE p.filename = :filename")
    Optional<PdfBillSave> findByName(@Param("filename") String filename);
}
