package com.example.monara_backend.controller;
import com.example.monara_backend.model.DesignerBillSend;
import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.service.DesignerBillSendService;
import com.example.monara_backend.service.ShowroomService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/showroom")
@RequiredArgsConstructor
public class ShowroomController {

    @Autowired
    private ShowroomService showroomService;

    @Autowired
    private DesignerBillSendService designerBillSendService;

    @PostMapping("/add")
    public String addFile(HttpServletRequest request, @RequestParam("file")MultipartFile file) throws IOException, SerialException, SQLException
    {
        byte[] bytes = file.getBytes();

        Blob blob = new SerialBlob(bytes);

        ShowroomFile fileUpload =new ShowroomFile();
        fileUpload.setName(file.getOriginalFilename());
        fileUpload.setDbFile(blob);
        showroomService.saveDetails(fileUpload);
        return "redirect:/";
    }

    @GetMapping("/viewBill")
    public List<DesignerBillSend> getAllFiles() {
        return designerBillSendService .getAllFiles();
    }
}
