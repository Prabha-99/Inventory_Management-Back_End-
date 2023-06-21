package com.example.monara_backend.controller;
import com.example.monara_backend.model.FileUpload;
import com.example.monara_backend.service.ShowroomService;
import com.example.monara_backend.service.ShowroomServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/showroom")
@RequiredArgsConstructor
public class ShowroomController {

    private ShowroomService showroomService;



    @GetMapping("/display")
    public ResponseEntity<byte[]>displayFile(@RequestParam("id")long id )throws IOException,SQLException
    {
        FileUpload file = showroomService.viewById(id);
        byte [] fileBytes;
        try (InputStream inputStream = new FileInputStream(String.valueOf(file))) {
            fileBytes = inputStream.readAllBytes();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.ALL);
        headers.setContentLength(fileBytes.length);

        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public String addFile(HttpServletRequest request, @RequestParam("dbFile")MultipartFile file) throws IOException, SerialException, SQLException
    {
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        FileUpload fileUpload =new FileUpload();
        fileUpload.setDbFile(blob);
        showroomService.create(fileUpload);
        return "redirect:/";

    }
}
