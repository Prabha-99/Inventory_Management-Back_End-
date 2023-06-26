package com.example.monara_backend.service;

import com.example.monara_backend.model.BillSave;
import com.example.monara_backend.model.Designer;
import com.example.monara_backend.repository.BillSaveRepo;
import com.example.monara_backend.repository.DesignerRepo;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class DesignerService {
    @Autowired
    private DesignerRepo designerRepo;

    public List<Designer> getAllDetails () {

        return designerRepo.findAll();
    }


}


