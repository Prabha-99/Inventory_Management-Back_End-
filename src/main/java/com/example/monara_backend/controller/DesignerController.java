package com.example.monara_backend.controller;
import com.example.monara_backend.model.ShowroomFile;


import com.example.monara_backend.service.ShowroomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/designer")
@Configuration



public class DesignerController {
    @Autowired
    private ShowroomService showroomService;


    @GetMapping("/files")
    //@GetMapping("/download/{id}")

    public List<ShowroomFile> getAllFiles() {
        return showroomService.getAllFiles();
    }

    public class JacksonConfig {

        @Bean
        public ObjectMapper objectMapper() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            return objectMapper;
        }


    /*public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) throws SQLException {
        ShowroomFile file = showroomService.getFileById(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        // Stream the file content to the response
        return ResponseEntity.ok()
                .headers(headers)
                .body(file.getDbFile().getBytes(1, (int) file.getDbFile().length()));
    }*/


    }
}
