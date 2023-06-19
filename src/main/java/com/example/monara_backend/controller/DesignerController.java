package com.example.monara_backend.controller;

import com.example.monara_backend.model.Designer;
import com.example.monara_backend.repository.DesignerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dbfile")
public class DesignerController {

    @Autowired
    private DesignerRepo designerRepo;

    @GetMapping("/allfile")
    public @ResponseBody Iterable<Designer> getAllDesinger(){
        return designerRepo.findAll();
    }
}

