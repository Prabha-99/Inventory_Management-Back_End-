package com.example.monara_backend.service;

import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.repository.BillSaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillSaveService {
    @Autowired
    private BillSaveRepo billSaveRepo;

    public void savePdf(byte[] pdfData) {
        BillSave pdf = new BillSave();
        pdf.setPdf_data(pdfData);
        billSaveRepo.save(pdf);
    }

}
