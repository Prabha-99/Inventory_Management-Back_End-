package com.example.monara_backend.repository;

import com.example.monara_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepo extends JpaRepository<Report, Long> {
    List<Report> findAll();
}
