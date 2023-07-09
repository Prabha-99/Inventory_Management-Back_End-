package com.example.monara_backend.repository;

import com.example.monara_backend.model.PdfBillSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PdfBillRepo extends JpaRepository<PdfBillSave,Long> {
    @Modifying
    @Query("DELETE FROM PdfBillSave c WHERE c.bill_id = :bill_id")
    void deleteById(@Param("bill_id") Long id);


}
