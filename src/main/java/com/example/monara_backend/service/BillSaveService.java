package com.example.monara_backend.service;

import com.example.monara_backend.controller.BillSaveController;
import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.repository.BillSaveRepo;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillSaveService {

    @Autowired
    private BillSaveRepo billSaveRepo;

    public BillSave saveTransaction(BillSave billSave) {
        return billSaveRepo.save(billSave);
    }

}


