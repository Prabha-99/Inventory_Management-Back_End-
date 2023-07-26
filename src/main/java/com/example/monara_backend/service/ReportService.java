package com.example.monara_backend.service;

import com.example.monara_backend.model.Product;
import com.example.monara_backend.model.Report;
import com.example.monara_backend.model.ShowroomFile;
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
import java.util.*;


@Service
@RequiredArgsConstructor
public class ReportService {

    //System Reports are Automatically Generated Every day at 9.00PM

    private final JdbcTemplate jdbcTemplate;
    private final ProductRepo productRepo;
    private final ReportRepo reportRepo;


    //Local variable to Store current Data.
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String dateCreated = currentDate.format(formatter);




    @Scheduled(cron = "0 0 21 * * ?")
    public String exportStockReport() throws FileNotFoundException, JRException {
        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports";/*Declaring the Report path as a Global variable.
         *****This must be a path to DB*****/
        List<Product> products=productRepo.findAll();//Retrieving all User Data into a List

        //Loading the .jrxml file and Compiling it
        File file= ResourceUtils.getFile("classpath:Products.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        //Mapping List Data into the Report
        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(products);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Created by","Monara Creations pvt,Ltd");

        // Saving the report file to the database
        String sql = "INSERT INTO reports (report_name, path, date) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Stock_"+dateCreated+".pdf");
            ps.setString(2,reportPath+"\\Stock_"+dateCreated+".pdf");
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // set the current date and time
            return ps;
        }, keyHolder);

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
        JasperExportManager.exportReportToPdfFile(print,reportPath+"\\Stock_"+dateCreated+".pdf");


        return "Report generated Successfully at : "+reportPath;
    }

    public Report getFileById(Long id) {
        return reportRepo.findById(id).orElse(null);
    }

}
