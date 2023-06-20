package com.example.monara_backend.controller;
import com.example.monara_backend.model.FileUpload;
import com.example.monara_backend.service.ShowroomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
public class ShowroomController {

    @Autowired
    private ShowroomService showroomService;

    @GetMapping("/ping")
    @ResponseBody
    public String hello_world(){
        return "Hello World!";
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
