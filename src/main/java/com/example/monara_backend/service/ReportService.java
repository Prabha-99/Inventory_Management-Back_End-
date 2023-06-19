package com.example.monara_backend.service;

import com.example.monara_backend.dto.ReportDto;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.Report;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.ProductRepo;
import com.example.monara_backend.repository.ReportRepo;
import com.example.monara_backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    //System Reports are Automatically Generated Every day at 9.00PM

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private UserRepo userRepo;
    private ProductRepo productRepo;
    private ReportRepo reportRepo;

    public ReportService(JdbcTemplate jdbcTemplate, UserRepo userRepo, ProductRepo productRepo, ReportRepo reportRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.reportRepo = reportRepo;
    }

    //Local variable to Store current Data.
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String dateCreated = currentDate.format(formatter);







//    public List<Report> getDocumentMetadata() {
//        List<Report> metadataList = new ArrayList<>();
//
//        List<Report> documentList = reportRepo.findAll();
//
//        for (Report document : documentList) {
//            Report metadata = new Report();
//            metadata.setReport_id(document.getReport_id());
//            metadata.setReport_name(document.getReport_name());
//            metadata.setData(document.getData());
//            metadata.setDate(document.getDate());
//
//            metadataList.add(metadata);
//        }
//
//        return metadataList;
//    }







    //    public String exportUserReport(String format) throws FileNotFoundException, JRException {
//        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports";/*Declaring the Report path as a Global variable.
//                                                                               *****This must be a path to DB*****/
//        List<User> users=userRepo.findAll();//Retrieving all User Data into a List
//
//        //Loading the .jrxml file and Compiling it
//        File file= ResourceUtils.getFile("classpath:SystemUsers.jrxml");
//        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
//
//        //Mapping List Data into the Report
//        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(users);
//        Map<String,Object> parameters=new HashMap<>();
//        parameters.put("Created by","Monara Creations pvt,Ltd");
//
//        //Printing the Report
//        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
//        if(format.equalsIgnoreCase("html")){
//            JasperExportManager.exportReportToHtmlFile(print,reportPath+"\\Users.html");
//        }if(format.equalsIgnoreCase("pdf")){
//            JasperExportManager.exportReportToPdfFile(print,reportPath+"\\Users.pdf");
//        }
//        return "Report generated Successfully at : "+reportPath;
//    }







    public String exportUserReport(String format) throws IOException, JRException {

        List<User> users=userRepo.findAll();//Retrieving all User Data into a List

        //Loading the .jrxml file and Compiling it
        File file= ResourceUtils.getFile("classpath:SystemUsers.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        //Mapping List Data into the Report
        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(users);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Created by","Monara Creations pvt,Ltd");

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);

        // Converting the JasperPrint object to a byte array
        byte[] reportBytes = JasperExportManager.exportReportToPdf(print);

        // Saving the report file to the database
        String sql = "INSERT INTO reports (report_name, format, data, date) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Users");
            ps.setString(2, format);
            ps.setBytes(3, reportBytes);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // set the current date and time
            return ps;
        }, keyHolder);

        // Retrieving the ID of the inserted row
        long reportId = keyHolder.getKey().longValue();




        // Saving the report file to the local file system
        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports\\" +dateCreated + "." + format.toLowerCase();
        FileOutputStream fos = new FileOutputStream(reportPath);
        fos.write(reportBytes);
        fos.close();

        return "Report generated and saved Successfully at : "+reportPath;
    }


//    public String exportUserReport(String format) throws IOException, JRException {
//
//        List<User> users=userRepo.findAll();//Retrieving all User Data into a List
//
//        //Loading the .jrxml file and Compiling it
//        File file= ResourceUtils.getFile("classpath:SystemUsers.jrxml");
//        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
//
//        //Mapping List Data into the Report
//        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(users);
//        Map<String,Object> parameters=new HashMap<>();
//        parameters.put("Created by","Monara Creations pvt,Ltd");
//
//        //Printing the Report
//        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
//
//        //Local variable to Store current Data.
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String dateCreated = currentDate.format(formatter);
//
//        //File Path
//        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports\\" +dateCreated + "." + format.toLowerCase();
//
//        // Saving the report file to the database
//        String sql = "INSERT INTO reports (report_name, format, data, date) VALUES (?, ?, ?, ?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, "Users");
//            ps.setString(2, format);
//            ps.setString(3, reportPath);
//            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // set the current date and time
//            return ps;
//        }, keyHolder);
//
//        // Retrieving the ID of the inserted row
//        long reportId = keyHolder.getKey().longValue();
//
//
//
//
//        // Saving the report file to the local file system
//
//        FileOutputStream fos = new FileOutputStream(reportPath);
//        fos.write(reportBytes);
//        fos.close();
//
//        return "Report generated and saved Successfully at : "+reportPath;
//    }




    @Scheduled(cron = "0 0 21 * * ?")
    public String exportProductReport() throws FileNotFoundException, JRException {
        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports";/*Declaring the Report path as a Global variable.
         *****This must be a path to DB*****/
        List<Product> users=productRepo.findAll();//Retrieving all User Data into a List

        //Loading the .jrxml file and Compiling it
        File file= ResourceUtils.getFile("classpath:AllProducts.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        //Mapping List Data into the Report
        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(users);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Created by","Monara Creations pvt,Ltd");

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
        JasperExportManager.exportReportToPdfFile(print,reportPath+"\\Products.pdf");


        return "Report generated Successfully at : "+reportPath;
    }

    @Scheduled(cron = "0 0 21 * * ?")
    public String exportPSReport() throws FileNotFoundException, JRException {
        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports";/*Declaring the Report path as a Global variable.
         *****This must be a path to DB*****/
        List<Product> users=productRepo.findAll();//Retrieving all User Data into a List

        //Loading the .jrxml file and Compiling it
        File file= ResourceUtils.getFile("classpath:JobSpecReport.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        //Mapping List Data into the Report
        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(users);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Created by","Monara Creations pvt,Ltd");


        // Saving the report file to the database
        String sql = "INSERT INTO reports (report_name, path, date) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Users"+dateCreated);
            ps.setString(2,reportPath);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // set the current date and time
            return ps;
        }, keyHolder);

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
        //Save the Report in the Local File System
        JasperExportManager.exportReportToPdfFile(print,reportPath+"\\ProductSpec.pdf");

        return "Report generated Successfully at : "+reportPath;
    }





    @Scheduled(cron = "0 0 21 * * ?")
    public String exportGIN() throws FileNotFoundException, JRException {
        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports";/*Declaring the Report path as a Global variable.
         *****This must be a path to DB*****/
        List<Product> users=productRepo.findAll();//Retrieving all User Data into a List

        //Loading the .jrxml file and Compiling it
        File file= ResourceUtils.getFile("classpath:GIN.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        //Mapping List Data into the Report
        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(users);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Created by","Monara Creations pvt,Ltd");

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
            JasperExportManager.exportReportToPdfFile(print,reportPath+"\\GIN.pdf");

        return "Report generated Successfully at : "+reportPath;
    }


    public List<ReportDto> getAllFiles() {
        List<Report> fileEntities = reportRepo.findAll();
        // Convert FileEntity objects to FileDTO objects
        List<ReportDto> files = fileEntities.stream()
                .map(this::convertToFileDTO)
                .collect(Collectors.toList());
        return files;
    }

    private ReportDto convertToFileDTO(Report report) {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(fileEntity.getId());
        reportDto.setFileName(fileEntity.getFileName());
        reportDto.setCreatedDate(fileEntity.getCreatedDate());
        reportDto.setFilePath(fileEntity.getFilePath());
        return fileDTO;
    }
}
