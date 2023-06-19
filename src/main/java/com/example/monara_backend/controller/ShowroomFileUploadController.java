package com.example.monara_backend.controller;

import com.example.monara_backend.dto.ShowroomFileDocument;
import com.example.monara_backend.dto.ShowroomFileUploadResponse;

import com.example.monara_backend.repository.ShowroomRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController


@RequestMapping("/api/file")
public class ShowroomFileUploadController {

    private ShowroomRepo showroomRepo;

    public ShowroomFileUploadController(ShowroomRepo showroomRepo) {
        this.showroomRepo = showroomRepo;
    }

    @PostMapping("uploadDb")
    ShowroomFileUploadResponse FileUpload(@RequestParam("file")MultipartFile file) throws IOException {

        String name = StringUtils.cleanPath(file.getOriginalFilename());
        ShowroomFileDocument ShowroomFileDocument = new ShowroomFileDocument();
        ShowroomFileDocument.setFileName(name);
        ShowroomFileDocument.setShowroomDocFile(file.getBytes());


        showroomRepo.save(ShowroomFileDocument);


        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFormDB/")
                .path(name)
                .toString();

        String contentType = file.getContentType();
        ShowroomFileUploadResponse response = new ShowroomFileUploadResponse(name,contentType,url);

        return response;

    }

}