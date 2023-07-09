package com.example.monara_backend.controller;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.service.GINService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/GIN")
@RequiredArgsConstructor
public class GINController {

    private final GINService ginService;


    @GetMapping("/generateGIN")
    public String generateGINReport() throws JRException, FileNotFoundException {
        return ginService.exportGIN();
    }

//    @GetMapping("/callGIN")
//    public GIN addGIN(GINService ginService) {
//        ginService.saveGINData(GIN);
//    }

    @PostMapping("/submit")
    public void submitGINData(@RequestBody GIN ginData){
        ginService.saveGINData(ginData);
    }

    @GetMapping("/getAllGin")
    public List<GIN> getAllGin() {
        return ginService.getAllGin();
    }
}
