package com.example.monara_backend.service;

import com.example.monara_backend.model.Designer;
import com.example.monara_backend.repository.DesignerRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DesignerService {
    private final DesignerRepo designerRepo;

    public DesignerService(DesignerRepo designerRepo){
        this.designerRepo = designerRepo;
    }

    public List<Designer>getAllDesigner(){

    }

}
