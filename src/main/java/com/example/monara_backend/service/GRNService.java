package com.example.monara_backend.service;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.GRN;
import com.example.monara_backend.model.Product;
import com.example.monara_backend.repository.GINRepo;
import com.example.monara_backend.repository.GRNRepo;
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

public class GRNService {

    private final JdbcTemplate jdbcTemplate;
    private final GRNRepo grnRepo;
    private final ExecutorService executorService;

    public GRNService(JdbcTemplate jdbcTemplate, GRNRepo grnRepo, ExecutorService executorService) {
        this.jdbcTemplate = jdbcTemplate;
        this.grnRepo = grnRepo;
        this.executorService = executorService;
    }

    public String exportGRN() throws FileNotFoundException, JRException {
        String reportPath = "F:\\Uni Works\\Level 3\\Sem 1\\Group Project\\Reports";/*Declaring the Report path as a Global variable.
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
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "GRN");
            ps.setString(2,supplierName );
            ps.setString(3,reportPath);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // set the current date and time
            return ps;
        }, keyHolder);

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
        JasperExportManager.exportReportToPdfFile(print,reportPath+"\\GRN.pdf");

        return "Report generated Successfully at : "+reportPath;
    }

    public void saveGRNData(GRN grnData) {
        executorService.execute(() -> {
            try {
                // Simulate some processing time
                Thread.sleep(2000);

                // Save GIN data to database table
                grnRepo .save(grnData);

                System.out.println("GIN data saved successfully: " + grnData );

                // Perform another concurrent task
//                performConcurrentTask(ginData);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
