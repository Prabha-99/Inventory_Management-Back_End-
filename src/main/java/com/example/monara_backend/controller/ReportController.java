package com.example.monara_backend.controller;

import com.example.monara_backend.model.Report;
import com.example.monara_backend.repository.ReportRepo;
import com.example.monara_backend.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepo reportRepo;

    @GetMapping("/userReport/{format}")
    public String generateUserReport(@PathVariable String format) throws JRException, IOException {
        return reportService.exportUserReport(format);
    }

    @GetMapping("/productReport/{format}")
    public String generateProductReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportProductReport(format);
    }

    @GetMapping("/psReport/{format}")
    public String generatePSReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportPSReport(format);
    }


    @GetMapping("/metadata")
    public List<Report> getDocumentMetadata() {
        return reportService.getDocumentMetadata();
    }

    @GetMapping("/all")
    public List<Report>getAllDocs(Report report){
        return reportRepo.findAll();
    }
}
