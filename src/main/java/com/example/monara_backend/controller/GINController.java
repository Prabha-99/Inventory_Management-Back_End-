package com.example.monara_backend.controller;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.service.GINService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("api/GIN")

public class GINController {

    private final GINService ginService;

    public GINController(GINService ginService) {
        this.ginService = ginService;
    }

    @GetMapping("/generateGIN")
    public String generateGINReport() throws JRException, FileNotFoundException {
        return ginService.exportGIN();
    }

//    @GetMapping("/callGIN")
//    public GIN addGIN(GINService ginService) {
//        ginService.saveGINData(GIN);
//    }

    @PostMapping("/submit")
    public void submitGINData(@RequestBody GIN ginData) {
        ginService.saveGINData(ginData);
    }

}
