package com.example.monara_backend.repository;

import com.example.monara_backend.model.DesignerBillSend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerBillSendRepo extends JpaRepository<DesignerBillSend,Integer> {
}
