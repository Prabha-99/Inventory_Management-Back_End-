package com.example.monara_backend.service;

import com.example.monara_backend.model.DesignerBillSend;

import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.repository.DesignerBillSendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignerBillSendService {
    @Autowired
    private DesignerBillSendRepo designerBillSendRepo;

    public DesignerBillSend saveBill(DesignerBillSend designerBillSend) {

        return designerBillSendRepo.save(designerBillSend);
    }
    public List<DesignerBillSend> getAllFiles() {

        return designerBillSendRepo.findAll();
    }
}
