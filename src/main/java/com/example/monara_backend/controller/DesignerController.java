package com.example.monara_backend.controller;
import com.example.monara_backend.model.DesignerBillSend;
import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.service.DesignerBillSendService;
import com.example.monara_backend.service.ShowroomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/designer")
@Configuration


public class DesignerController {
    @Autowired
    private ShowroomService showroomService;

    @Autowired
    private DesignerBillSendService designerBillSendService;

    @GetMapping("/files")

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
    }

    @PostMapping("/billSend")
    public String addFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException, SerialException, SQLException
    {
        byte[] bytes = file.getBytes();

        Blob blob = new SerialBlob(bytes);

        DesignerBillSend fileUpload =new DesignerBillSend();
        fileUpload.setFileName(file.getOriginalFilename());
        fileUpload.setDbFile(blob);
        designerBillSendService.saveBill(fileUpload);
        return "redirect:/";

    }

}
