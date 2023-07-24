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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam Integer id) {
        DesignerBillSend file = designerBillSendService.getFileById(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        String filePath = file.getFilePath();
        File downloadFile = new File(filePath);

        if (!downloadFile.exists() || !downloadFile.isFile()) {
            return ResponseEntity.notFound().build();
        }

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName());

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
