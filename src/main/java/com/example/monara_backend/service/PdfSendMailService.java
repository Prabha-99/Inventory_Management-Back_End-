package com.example.monara_backend.service;


import com.example.monara_backend.model.PdfBillSave;
import com.example.monara_backend.repository.PdfGetFileRepo;
import com.example.monara_backend.repository.PdfSendRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class PdfSendMailService {
    @Autowired
    private PdfSendRepo pdfSendRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PdfGetFileRepo pdfGetFileRepo;

    public void sendBillByEmail(Long bill_id) throws IOException, MessagingException {

        // Get the email address from the "email_addresses" table
        String emailAddress = pdfSendRepo.findEmailAddressByBillId(bill_id);

        String Qu_Number = pdfSendRepo.findQuatationNoById(bill_id);

        String Cu_Name = pdfSendRepo.findNameById(bill_id);

        //Get filename
        String filename = pdfSendRepo.findPdfFileNameByBillId(bill_id);
        byte[] fileContent = downloadFileFromFileSystem(filename);

        // Create a MimeMessage object
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set the email properties
        helper.setFrom("inventory.monara@gmail.com");
        helper.setTo(emailAddress);
        helper.setSubject("MONARA CREATIONS (pvt) ltd." + bill_id);

        String TextMail="<h2>MONARA CREATIONS (pvt) ltd.</h2> <br> " +
                "Please find attached PDF file <br>" +
                " Quotation_no: " + Qu_Number +
                "<br> Name: " + Cu_Name +"<br><br>" +
                "<br> No.262,High level Road,<br>" +
                " Nugegoda.,Sri Lanka.<br>" +
                "Web : www.monaracreations.lk<br><br>"+
                "<br> This is a system generated email. Do not reply!!<br><br>"+
                "Thank You for Your Attention!<br>"+
                "Have a Nice Day!!<br>";

        helper.setText(TextMail,true);
        // Attach the PDF file to the email
        ByteArrayResource file = new ByteArrayResource(fileContent);
        helper.addAttachment(filename, file);
        // Send the email
        javaMailSender.send(message);
    }
    public byte[] downloadFileFromFileSystem(String filename) throws IOException {
        Optional<PdfBillSave> fileData = pdfGetFileRepo.findByName(filename);
        String filePath=fileData.get().getFilepath();
        byte[] filePdf = Files.readAllBytes(new File(filePath).toPath());
        return filePdf;
    }

}
