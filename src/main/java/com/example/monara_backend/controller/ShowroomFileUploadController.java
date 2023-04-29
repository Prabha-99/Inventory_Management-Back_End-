package com.example.monara_backend.controller;

import com.example.monara_backend.dto.ShowroomFileDocument;
import com.example.monara_backend.dto.ShowroomFileUploadResponse;
import com.example.monara_backend.service.ShowroomDocFile;

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
public class ShowroomFileUploadController {

    private ShowroomDocFile showroomDocFile;

    public ShowroomFileUploadController(ShowroomDocFile showroomDocFile) {
        this.showroomDocFile = showroomDocFile;
    }

    @PostMapping("single/uploadDb")
    ShowroomFileUploadResponse FileUpload(@RequestParam("file")MultipartFile file) throws IOException {

        String name = StringUtils.cleanPath(file.getOriginalFilename());
        ShowroomFileDocument ShowroomFileDocument = new ShowroomFileDocument();
        ShowroomFileDocument.setFileName(name);
        ShowroomFileDocument.setShowroomDocFile(file.getBytes());


        showroomDocFile.save(ShowroomFileDocument);


        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFormDB/")
                .path(name)
                .toString();

        String contentType = file.getContentType();
        ShowroomFileUploadResponse response = new ShowroomFileUploadResponse(name,contentType,url);

        return response;

    }
    @GetMapping("/downloadFormDB/{fileName}")
    ResponseEntity<Object> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
        ShowroomFileDocument doc = ShowroomDocFile.findByFileName(fileName);


        String mimeType = request.getServletContext().getMimeType(doc.getFileName());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename" + doc.getFilename())
                .body(doc.getDocFile());
    }
}
