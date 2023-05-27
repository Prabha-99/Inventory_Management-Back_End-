package com.example.monara_backend.service;

import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.repository.BillSaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BillSaveService {

    @Autowired
    private BillSaveRepo billSaveRepo;


    public BillSave FormData (BillSave bill) {
        return billSaveRepo.save(bill);
    }

    public List<BillSave> getAllDetails () {
        return billSaveRepo.findAll();
    }

    public void deleteBill (Integer bill_id) {
        billSaveRepo.deleteById(bill_id);
    }

}



