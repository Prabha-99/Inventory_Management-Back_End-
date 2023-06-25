package com.example.monara_backend.controller;
import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.service.ShowroomService;
import com.example.monara_backend.service.ShowroomServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/showroom")
@RequiredArgsConstructor
public class ShowroomController {

    private ShowroomService showroomService;

    @PostMapping("/add")
    public String addFile(HttpServletRequest request, @RequestParam("file")MultipartFile file) throws IOException, SerialException, SQLException
    {
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        ShowroomFile fileUpload =new ShowroomFile();
        fileUpload.setName(file.getOriginalFilename());
        fileUpload.setDbFile(blob);
        showroomService.create(fileUpload);
        return "redirect:/";

    }
}
