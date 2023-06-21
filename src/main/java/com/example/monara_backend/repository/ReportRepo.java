package com.example.monara_backend.repository;

import com.example.monara_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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


}
