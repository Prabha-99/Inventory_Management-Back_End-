package com.example.monara_backend.service;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.GINRepo;
import com.example.monara_backend.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Service

public class GINService {

    private final JdbcTemplate jdbcTemplate;
    private final GINRepo ginRepo;
    private final ExecutorService executorService;

    public GINService(JdbcTemplate jdbcTemplate, GINRepo ginRepo, ExecutorService executorService) {
        this.jdbcTemplate = jdbcTemplate;
        this.ginRepo = ginRepo;
        this.executorService = executorService;
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

        return "Report generated Successfully at : "+reportPath;
    }

    public void saveGINData(GIN ginData) {
        executorService.execute(() -> {
            try {
                // Simulate some processing time
                Thread.sleep(2000);

                // Save GIN data to database table
                ginRepo.save(ginData);

                System.out.println("GIN data saved successfully: " + ginData);

                // Perform another concurrent task
//                performConcurrentTask(ginData);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
