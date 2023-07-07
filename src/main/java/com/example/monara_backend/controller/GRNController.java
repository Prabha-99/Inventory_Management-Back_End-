package com.example.monara_backend.controller;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.GRN;
import com.example.monara_backend.service.GRNService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("api/GRN")
@RequiredArgsConstructor
public class GRNController {

    private final GRNService grnService;


    @GetMapping("/generateGRN")
    public String generateGRNReport() throws JRException, FileNotFoundException {
        return grnService.exportGRN();
    }

    @PostMapping("/submit")
    public void submitGRNData(@RequestBody GRN grnData) {
        grnService.saveGRNData(grnData);
    }
}
