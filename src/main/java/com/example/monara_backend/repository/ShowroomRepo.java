package com.example.monara_backend.repository;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.ShowroomFile;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowroomRepo extends JpaRepository<ShowroomFile,Integer> {

    @Query(value = "SELECT * FROM showroom WHERE TIME(date) >= CURTIME() ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the Newest Architectural Report
    List<ShowroomFile> newestArchi();

    @Query(value = "SELECT name FROM showroom ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the name of the Newest Architectural Report
    String nameOFNewestArchi();

}

