package com.example.monara_backend.controller;

import com.example.monara_backend.service.GINService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("api/GIN")
@RequiredArgsConstructor
public class GINController {

    private final GINService ginService;

    @GetMapping("/generateGIN")
    public String generateGINReport() throws JRException, FileNotFoundException {
        return ginService.exportGIN();
    }
}
