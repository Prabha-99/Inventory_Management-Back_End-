package com.example.monara_backend.service;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.GRN;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.GRNRepo;
import jakarta.mail.MessagingException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Service

public class GRNService {

    private final JdbcTemplate jdbcTemplate;
    private final GRNRepo grnRepo;
    private final ExecutorService executorService;
    private final NotificationService notificationService;

    public GRNService(JdbcTemplate jdbcTemplate, GRNRepo grnRepo, ExecutorService executorService, NotificationService notificationService) {
        this.jdbcTemplate = jdbcTemplate;
        this.grnRepo = grnRepo;
        this.executorService = executorService;
        this.notificationService = notificationService;
    }


    List<String> recipientEmails = Arrays.asList(      /*This email list should get From the Database not like this*/
            "prabhashana77@gmail.com"
    );
    String attachmentPath = "C:/Users/milin/Documents/SPRING/GIT12/reports/";

    public String getGRNName(){
        return grnRepo.nameOFNewestGRN();
    }
    public void saveGRNData(GRN grnData) {
        executorService.execute(() -> {
            try {
                // Simulate some processing time
                Thread.sleep(0);
                String reportName=getGRNName();
                String path=attachmentPath+reportName+".pdf";

                grnRepo.save(grnData);// 1. Save GRN data to database table
                exportGRN();// 2. Generating the Report

                for (String recipientEmail : recipientEmails) { // 3. Sending the Notification
                    notificationService.GRNNotification(recipientEmail,path);
                }

            } catch (InterruptedException | MessagingException | FileNotFoundException | JRException e) {
                e.printStackTrace();
            }
        });
    }

    public String exportGRN() throws FileNotFoundException, JRException {
        String reportPath = "C:\\Users\\milin\\Documents\\SPRING\\GIT12\\reports";/*Declaring the Report path as a Global variable.
         *****This must be a path to DB*****/
        List<GRN> grns=grnRepo.newestGRN();//Retrieving Newest GRN Data into a List

        //Loading the .jrxml file and Compiling it
        File file= ResourceUtils.getFile("classpath:GRN.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        //Mapping List Data into the Report
        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(grns);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Created by","Monara Creations pvt,Ltd");

        // Saving the report file to the database
        String sql = "INSERT INTO reports (report_name, customer, path, date) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String supplierName = grns.get(0).getSupplier_name(); // Assuming customer_name is retrieved from the first GRN object

        Long invoiceNo =grns.get(0).getInvoice_no(); // Assuming invoice_no is retrieved from the first GIN object
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, "GRN_"+invoiceNo+".pdf");
            ps.setString(2,supplierName );
            ps.setString(3,reportPath+"\\GRN_"+invoiceNo+".pdf");
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // set the current date and time
            return ps;
        }, keyHolder);

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
        JasperExportManager.exportReportToPdfFile(print,reportPath+"\\GRN_"+invoiceNo+".pdf");

        return "Report generated Successfully at : "+reportPath;
    }



    public List<GRN> getAllGrn () {
        return grnRepo.findAll();
    }



}
