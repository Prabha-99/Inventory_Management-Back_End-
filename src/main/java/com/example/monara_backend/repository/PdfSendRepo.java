package com.example.monara_backend.repository;

import com.example.monara_backend.model.BillSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfSendRepo extends JpaRepository<BillSave,Long> {
    @Query("SELECT pd.filename FROM PdfBillSave pd WHERE pd.bill_id = :bill_id")
    String findPdfFileNameByBillId(@Param("bill_id") Long bill_id);

    @Query("SELECT bi.other FROM BillSave bi WHERE bi.bill_id = :bill_id")
    String findEmailAddressByBillId(@Param("bill_id") Long bill_id);

    @Query("SELECT bi2.qu_no FROM BillSave bi2 WHERE bi2.bill_id=:bill_id")
    String findQuatationNoById(@Param("bill_id")Long bill_id);
}
