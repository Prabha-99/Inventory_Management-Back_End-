package com.example.monara_backend.service;

import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.repository.BillSaveRepo;
import com.example.monara_backend.repository.PdfBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillSaveService {

        @Autowired
        private BillSaveRepo billSaveRepo;

        @Autowired
        private PdfBillRepo pdfBillRepo;


        public BillSave FormData (BillSave bill) {
            return billSaveRepo.save(bill);
        }

        public List<BillSave> getAllDetails () {
            return billSaveRepo.findAll();
        }


    public void deleteById(Long bill_id) {
        Optional<BillSave> parentOptional = billSaveRepo.findById(bill_id);
        parentOptional.ifPresent(billSave -> {
            pdfBillRepo.deleteById(billSave.getBill_id());
            billSaveRepo.delete(billSave);
        });
    }
}
