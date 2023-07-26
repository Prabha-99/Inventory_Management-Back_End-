package com.example.monara_backend.controller;

import com.example.monara_backend.model.Report;
import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.repository.ReportRepo;
import com.example.monara_backend.service.ReportService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepo reportRepo;

    @GetMapping("/productReport")
    public String generateProductReport() throws JRException, FileNotFoundException {
        return reportService.exportProductReport();
    }

    @GetMapping("/psReport")
    public String generatePSReport() throws JRException, FileNotFoundException {
        return reportService.exportPSReport();
    }


    @GetMapping("getAllPSReport")
    public List<Report> getAllPS(){
        // Retrieve all files from the database
        List<Report> files = reportRepo.PSReports();
        return files;
    }

    @GetMapping("getAllGINReport")
    public List<Report> getAllGIN(){
        // Retrieve all files from the database
        List<Report> files = reportRepo.GINReports();
        return files;
    }

    @GetMapping("getAllGRNReport")
    public List<Report> getAllGRN(){
        // Retrieve all files from the database
        List<Report> files = reportRepo.GRNReports();
        return files;
    }

    //File Download Endpoint

//    @GetMapping("/{fileId}/download")
//    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws FileNotFoundException {
//        // Retrieve the file record from the database
//        Optional<Report> optionalFile = reportRepo.findById(fileId);
//        if (optionalFile.isPresent()) {
//            Report report = optionalFile.get();
//
//            // Create a Resource object from the report's local path
//            Resource resource = new FileSystemResource(report.getPath());
//            if (resource.exists()) {
//                // Return the report as a downloadable attachment
//                return ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + report.getReport_name() + "\"")
//                        .body(resource);
//            } else {
//                throw new FileNotFoundException("File not found: " + report.getPath());
//            }
//        } else {
//            throw new NoSuchElementException("File not found with ID: " + fileId);
//        }
//    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam Long id) {
        Report file = reportService.getFileById(id);
//        Optional<Report> file = reportRepo.findById(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        // Get the file path from the ShowroomFile entity
        String filePath = file.getPath();
        File downloadFile = new File(filePath);

        if (!downloadFile.exists() || !downloadFile.isFile()) {
            return ResponseEntity.notFound().build();
        }

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getReport_name());

        // Create an InputStreamResource from the file path
        InputStreamResource inputStreamResource;
        try {
            inputStreamResource = new InputStreamResource(new FileInputStream(downloadFile));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        // Stream the file content to the response
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(downloadFile.length())
                .body(inputStreamResource);
    }




}
