package com.example.monara_backend.controller;

import com.example.monara_backend.service.ShowroomDocFile;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowroomFileUploadController {

    private ShowroomDocFile showroomDocFile;

    public ShowroomFileUploadController(ShowroomDocFile showroomDocFile) {
        this.showroomDocFile = showroomDocFile;
    }


}
