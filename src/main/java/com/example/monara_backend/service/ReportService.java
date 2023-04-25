package com.example.monara_backend.service;

import com.example.monara_backend.model.Report;
import com.example.monara_backend.model.User;
import com.example.monara_backend.repository.ProductRepo;
import com.example.monara_backend.repository.ReportRepo;
import com.example.monara_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

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

    public List<Report> getDocumentMetadata() {
        List<Report> metadataList = new ArrayList<>();

        List<Report> documentList = reportRepo.findAll();

        for (Report document : documentList) {
            Report metadata = new Report();
            metadata.setReport_id(document.getReport_id());
            metadata.setReport_name(document.getReport_name());
            metadata.setData(document.getData());
            metadata.setDate(document.getDate());

            metadataList.add(metadata);
        }

        return metadataList;
    }

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

        //Local variable to Store current Data.
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateCreated = currentDate.format(formatter);


        // Saving the report file to the local file system
        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports\\" +dateCreated + "." + format.toLowerCase();
        FileOutputStream fos = new FileOutputStream(reportPath);
        fos.write(reportBytes);
        fos.close();

        return "Report generated and saved Successfully at : "+reportPath;
    }

    public String exportProductReport(String format) throws FileNotFoundException, JRException {
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
        if(format.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(print,reportPath+"\\Products.html");
        }if(format.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(print,reportPath+"\\Products.pdf");
        }
        return "Report generated Successfully at : "+reportPath;
    }

    public String exportPSReport(String format) throws FileNotFoundException, JRException {
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

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
        if(format.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(print,reportPath+"\\ProductSpec.html");
        }if(format.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(print,reportPath+"\\ProductSpec.pdf");
        }
        return "Report generated Successfully at : "+reportPath;
    }
}
