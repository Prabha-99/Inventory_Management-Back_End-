package com.example.monara_backend.repository;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GINRepo extends JpaRepository<GIN,Long> {

    @Query(value = "SELECT * FROM gin ORDER BY date DESC LIMIT 1", nativeQuery = true)//Getting the Newest GIN
    List<GIN> newestGIN();
}
