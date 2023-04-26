package com.example.monara_backend.service;

import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.repository.BillSaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillSaveService {

    @Autowired
    private BillSaveRepo billSaveRepo;


    public BillSave saveFormData(BillSave billSave) {
        return billSaveRepo.save(billSave);
    }
   /* public BillSave savebill(BillSave billSave) {
        return billSaveRepo.save(billSave);
    }*/

}


