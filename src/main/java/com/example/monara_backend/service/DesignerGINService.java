package com.example.monara_backend.service;

import com.example.monara_backend.model.DesignerBillSend;
import com.example.monara_backend.model.DesignerGIN;
import com.example.monara_backend.repository.DesignerGINRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesignerGINService {
    @Autowired
    private DesignerGINRepo designerGINRepo;

    public DesignerGIN saveGIN(DesignerGIN designerGIN) {

        return designerGINRepo.save(designerGIN);
    }
}
