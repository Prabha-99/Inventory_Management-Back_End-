package com.example.monara_backend.repository;

import com.example.monara_backend.model.GRN;
import com.example.monara_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    List<Report> findAll();

    @Query(value = "SELECT * FROM reports WHERE report_name LIKE 'U%'", nativeQuery = true)
    List<Report> userReports();

    @Query(value = "SELECT * FROM reports WHERE report_name LIKE 'P%'", nativeQuery = true)
    List<Report> PSReports();

    @Query(value = "SELECT * FROM reports WHERE report_name LIKE 'GIN%'", nativeQuery = true)
    List<Report> GINReports();

    @Query(value = "SELECT * FROM reports WHERE report_name LIKE 'GRN%'", nativeQuery = true)
    List<Report> GRNReports();


    @Query(value = "SELECT report_name FROM reports WHERE report_name LIKE 'Stock%' ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the name of the Newest Stock Report
    String nameOFNewestStock();

    @Query(value = "SELECT * FROM reports WHERE TIME(date) <= CURTIME() ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the Newest Stock Report
    List<Report> newestStock();

    @Query(value = "SELECT report_name FROM reports WHERE report_name LIKE 'Archi%' ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the name of the Newest Stock Report
    String nameOFNewestArchi();
}
