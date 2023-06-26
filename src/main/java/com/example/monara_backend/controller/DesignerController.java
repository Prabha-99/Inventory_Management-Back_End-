package com.example.monara_backend.controller;

import com.example.monara_backend.model.Designer;
import com.example.monara_backend.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/designer")

public class DesignerController {
    @Autowired
    private DesignerService designerService;

    @GetMapping("/view")
    public List<Designer> getAllDetails() {
        return designerService.getAllDetails();
    }

}

