package com.example.monara_backend.repository;

import com.example.monara_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    List<Report> findAll();

    @Query(value = "SELECT * FROM reports WHERE report_name LIKE 'U%'", nativeQuery = true)
    List<Report> userReports();

    @Query(value = "SELECT * FROM reports WHERE report_name LIKE 'P%'", nativeQuery = true)
    List<Report> PSReports();

    @Query(value = "SELECT * FROM reports WHERE report_name LIKE 'Stock%'", nativeQuery = true)
    List<Report> StockReports();

    @Query(value = "SELECT * FROM reports WHERE report_name LIKE 'GIN%'", nativeQuery = true)
    List<Report> GINReports();

    @Query(value = "SELECT * FROM reports WHERE report_name LIKE 'GRN%'", nativeQuery = true)
    List<Report> GRNReports();


    @Query(value = "SELECT path FROM reports WHERE report_id = :id", nativeQuery = true)
    String findFilePathById(@Param("id") Long id);

//    @Query("SELECT p FROM reports p WHERE p.filename = :filename", nativeQuery = true)
//    Optional<Report> findByName(@Param("filename") String filename);

    @Query(value = "SELECT * FROM reports WHERE report_name = :filename", nativeQuery = true)
    Optional<Report> findByName(@Param("filename") String filename);


    @Query(value = "SELECT report_name FROM reports WHERE report_name LIKE 'Stock%' ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the name of the Newest Stock Report
    String nameOFNewestStock();



}
