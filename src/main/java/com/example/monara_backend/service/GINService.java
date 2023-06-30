package com.example.monara_backend.service;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.repository.GINRepo;
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
import java.util.concurrent.Executors;

@Service

public class GINService {

    private final JdbcTemplate jdbcTemplate;
    private final GINRepo ginRepo;
    private final ExecutorService executorService;
    private final NotificationService notificationService;

    public GINService(JdbcTemplate jdbcTemplate, GINRepo ginRepo, ExecutorService executorService, NotificationService notificationService) {
        this.jdbcTemplate = jdbcTemplate;
        this.ginRepo = ginRepo;
        this.notificationService = notificationService;
        this.executorService = Executors.newFixedThreadPool(2); // Adjust the thread pool size as per your requirements
    }

    List<String> recipientEmails = Arrays.asList(      /*This email list should get From the Database not like this*/
            "prabhashana77@gmail.com"
    );

    public String getPath(){
        String path= ginRepo.pathToNewestGIN().toString();
//        path=path+"/GIN.pdf";
        return path;
    }

    public void saveGINData(GIN ginData) {
        executorService.execute(() -> {
            try {
                // Simulate some processing time
                Thread.sleep(2000);



                ginRepo.save(ginData);// 1. Save GIN data to database table

                exportGIN();// 2. Generating the Report

                for (String recipientEmail : recipientEmails) { // 3. Sending the Notification
                    notificationService.GINNotification(recipientEmail,getPath());
                }



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JRException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }










    public String exportGIN() throws FileNotFoundException, JRException {
        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports";/*Declaring the Report path as a Global variable.
         *****This must be a path to DB*****/
        List<GIN> gins=ginRepo.newestGIN();//Retrieving Newest GIN Data into a List

        //Loading the .jrxml file and Compiling it
        File file= ResourceUtils.getFile("classpath:GIN.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        //Mapping List Data into the Report
        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(gins);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Created by","Monara Creations pvt,Ltd");

        // Saving the report file to the database
        String sql = "INSERT INTO reports (report_name,customer, path, date) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String customerName = gins.get(0).getCustomer_name(); // Assuming customer_name is retrieved from the first GIN object
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "GIN");
            ps.setString(2,customerName );
            ps.setString(3,reportPath);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // set the current date and time
            return ps;
        }, keyHolder);

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
        JasperExportManager.exportReportToPdfFile(print,reportPath+"\\GIN.pdf");

        //Thread

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Report generated Successfully at : "+reportPath;


    }


}



