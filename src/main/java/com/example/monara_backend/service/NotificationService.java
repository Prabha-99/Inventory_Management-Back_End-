package com.example.monara_backend.service;

import com.example.monara_backend.common.Notification;
import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.GRN;
import com.example.monara_backend.model.Report;
import com.example.monara_backend.repository.GINRepo;
import com.example.monara_backend.repository.GRNRepo;
import com.example.monara_backend.repository.ReportRepo;
import com.example.monara_backend.repository.ShowroomRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class NotificationService implements Notification {

    @Autowired
    private JavaMailSender emailSender;


    private final GINRepo ginRepo;
    private final GRNRepo grnRepo;
    private final ReportRepo reportRepo;
    private final ShowroomRepo showroomRepo;



    String attachmentPath = "F:/Uni Works/Level 3/Sem 1/Group Project/Reports/";
    public String getGINName(){
        return ginRepo.nameOFNewestGIN();
    }

    public String getGRNName(){
        return grnRepo.nameOFNewestGRN();
    }

    public String getNewStockName(){
        return reportRepo.nameOFNewestStock();
    }

    public String getNewArchiName(){
        return showroomRepo.nameOFNewestArchi();
    }

    @Override
    public void productAddNotification(String recipientEmail, String productName, String Category, String Quantity) throws MessagingException {

        String reportName=getNewStockName();
        String path=attachmentPath+reportName;

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipientEmail);
        helper.setSubject("INVENTORY UPDATE : New Product Added ");
        String additionalText = "A new Product was added to the Inventory by Inventory_Admin. See below for further Details,<br><br> " +
                "<b>Product Category</b> : "+Category+ "<br> " +
                "<b>Product Name</b> : "+productName+ "<br><br> " +
                "<b>Product Name</b> : "+Quantity+ "<br><br> " +
                "Additionally, here the <mark>Product</mark> that has been <mark>Added</mark> to the Inventory.</mark> for the reference purpose.<br><br>";
        String tableContent = "<table style='border-collapse: collapse;'>" +
                "<tr>" +
                "<th style='border: 2px solid grey; padding: 8px;'>Category</th>" +
                "<th style='border: 2px solid grey; padding: 8px;'>Product</th>" +
                "<th style='border: 2px solid grey; padding: 8px;'>Quantity</th>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid grey; padding: 8px;'>" + Category + "</td>" +
                "<td style='border: 1px solid grey; padding: 8px;'>" + productName + "</td>" +
                "<td style='border: 1px solid grey; padding: 8px;'>" + Quantity + "</td>" +
                "</tr>" +
                "</table>";

        String greeting = "<br><b><i>This is an System Generated Email, Do not reply to this..!!!<br><br>Thank you for your attention <br> Have a nice Day.!!!</i></b>";


        String emailContent = additionalText +"\n"+ tableContent+ greeting;
        helper.setText(emailContent, true);

        // Attach the file
        FileSystemResource file = new FileSystemResource(new File(path));
        helper.addAttachment(file.getFilename(), file);

        emailSender.send(message);
    }




    @Override
    public void productDeleteNotification(String recipientEmail, String productName, String Category) throws MessagingException {

        String reportName=getNewStockName();
        String path=attachmentPath+reportName;

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("INVENTORY UPDATE : Existing Product Deleted !!! ");
        String additionalText = "An Existing Product was deleted by the Inventory_Admin. See below for further Details,<br><br> " +
                "<b>Product Category</b> : "+Category+ "<br> " +
                "<b>Product Name</b> : "+productName+ "<br><br> " +
                "Additionally, here the Product that has been <mark>Deleted from Inventory.</mark> for the reference purpose.<br><br>";

        String tableContent = "<table style='border-collapse: collapse;'>" +
                "<tr>" +
                "<th style='border: 2px solid grey; padding: 8px;'>Category</th>" +
                "<th style='border: 2px solid grey; padding: 8px;'>Product</th>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid grey; padding: 8px;'>" + Category + "</td>" +
                "<td style='border: 1px solid grey; padding: 8px;'>" + productName + "</td>" +
                "</tr>" +
                "</table>";

        String greeting = "<br><b><i>This is an System Generated Email, Do not reply to this..!!!<br><br>Thank you for your attention <br> Have a nice Day.!!!</i></b>";

        String emailContent = additionalText +"\n"+ tableContent+ greeting;
        helper.setText(emailContent, true);

        // Attach the file
        FileSystemResource file = new FileSystemResource(new File(path));
        helper.addAttachment(file.getFilename(), file);

        emailSender.send(message);
    }



    @Override
    public void GINNotification(String recipientEmail, String Path) throws MessagingException {

        List<GIN> gins=ginRepo.newestGIN();//Retrieving Newest GIN Data into a List
        Long invoice = gins.get(0).getInvoice_no();
        String customerName = gins.get(0).getCustomer_name();
        String mobile = gins.get(0).getContact_nu();

        String reportName=getGINName(); //here
        String path=attachmentPath+reportName;

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("NEW GOOD ISSUE NOTE - "+reportName);
        String additionalText = "Good Issue Note for,<br><br> <b>Invoice Number</b> : "+invoice+
                "<br> <b>Customer Name</b> : "+customerName+
                "<br> <b>Mobile</b> : "+mobile+"<br><br>" +
                "Additionally, below attached the <mark>Goods Issue Note (GIN)</mark> related to above goods issuance for the reference purpose.<br><br>";

        String greeting = "<b><i>This is an System Generated Email, Do not reply to this..!!!<br><br>Thank you for your attention <br> Have a nice Day.!!!</i></b>";

        String emailContent = additionalText +"\n"+ greeting;
        helper.setText(emailContent, true);


        // Attach the file
        FileSystemResource file = new FileSystemResource(new File(path));
        helper.addAttachment(file.getFilename(), file);

        emailSender.send(message);

    }





    @Override
    public void GRNNotification(String recipientEmail, String Path) throws MessagingException {

        List<GRN> grns=grnRepo.newestGRN();//Retrieving Newest GIN Data into a List
        Long invoice = grns.get(0).getInvoice_no();
        String supplierName = grns.get(0).getSupplier_name();
        String mobile = grns.get(0).getContact_nu();

        String reportName=getGRNName(); //here
        String path=attachmentPath+reportName;

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("NEW GOOD RECEIVED NOTE - "+reportName);
        String additionalText = "Good Received Note for,<br><br> <b>Invoice Number</b> : "+invoice+
                "<br> <b>Supplier</b> : "+supplierName+
                "<br> <b>Mobile</b> : "+mobile+"<br><br>" +
                "Additionally, below attached the <mark>Goods Received Note (GRN)</mark> related to above goods receiving for the reference purpose.<br><br>";

        String greeting = "<b><i>This is an System Generated Email, Do not reply to this..!!!<br><br>Thank you for your attention <br> Have a nice Day.!!!</i></b>";


        String emailContent = additionalText +"\n"+ greeting;
        helper.setText(emailContent, true);


        // Attach the file
        FileSystemResource file = new FileSystemResource(new File(path));
        helper.addAttachment(file.getFilename(), file);

        emailSender.send(message);
    }

    @Override
    public void newArchitecturalReport(String recipientEmail, String Path) throws MessagingException {

        String reportName=getNewArchiName(); //here
        String path=attachmentPath+reportName;

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("NEW ARCHITECTURAL REPORT - " + reportName);
        String additionalText = "Herewith below attached the Architectural Report <mark>" + reportName + "</mark><br><br>";

        String greeting = "<b><i>This is a System Generated Email, Do not reply to this..!!!<br><br>Thank you for your attention <br> Have a nice Day.!!!</i></b>";

        String emailContent = additionalText + "\n" + greeting;
        helper.setText(emailContent, true);

        // Attach the file
        FileSystemResource file = new FileSystemResource(new File(path));
        helper.addAttachment(file.getFilename(), file);

        emailSender.send(message);
    }


}
