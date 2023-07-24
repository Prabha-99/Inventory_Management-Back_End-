package com.example.monara_backend.controller;
import com.example.monara_backend.model.DesignerBillSend;
import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.service.DesignerBillSendService;
import com.example.monara_backend.service.NotificationService;
import com.example.monara_backend.service.ShowroomService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
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

    private final NotificationService notificationService;

    //Notification Emails List
    List<String> recipientEmails = Arrays.asList(      /*This email list should get From the Database not like this*/
            "prabhashana77@gmail.com"
    );

    @PostMapping("/add")
    public String addFile(HttpServletRequest request, @RequestParam("file")MultipartFile file) throws IOException, SerialException, SQLException, MessagingException {
        byte[] bytes = file.getBytes();

        Blob blob = new SerialBlob(bytes);

        ShowroomFile fileUpload =new ShowroomFile();
        fileUpload.setName(file.getOriginalFilename());
        fileUpload.setDbFile(blob);
        showroomService.saveDetails(fileUpload);

        // Send the notification to the Designer
        for (String recipientEmail : recipientEmails) {
            notificationService.newArchitecturalReport(recipientEmail,file.getOriginalFilename());
        }

        return "redirect:/";
    }

    @GetMapping("/viewBill")
    public List<DesignerBillSend> getAllFiles() {
        return designerBillSendService .getAllFiles();
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam Integer id) throws SQLException {
        DesignerBillSend file = designerBillSendService.getFileById(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName());

        // Stream the file content to the response
        return ResponseEntity.ok()
                .headers(headers)
                .body(file.getDbFile().getBytes(1, (int) file.getDbFile().length()));
    }

}
