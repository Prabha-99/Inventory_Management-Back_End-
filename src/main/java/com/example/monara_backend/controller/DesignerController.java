package com.example.monara_backend.controller;

import com.example.monara_backend.dto.ShowroomFileDocument;

import com.example.monara_backend.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/designer")
public class DesignerController {

    @Autowired
    private DesignerService designerService;

    @GetMapping("/getfiles")
        public List<ShowroomFileDocument> getDesigner(){
            return designerService.getAllDesigner();
    }
}

