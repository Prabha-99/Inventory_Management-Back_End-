package com.example.monara_backend.repository;

import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.GRN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GRNRepo extends JpaRepository<GRN,Long> {

    @Query(value = "SELECT * FROM grn ORDER BY date DESC LIMIT 1", nativeQuery = true)//Getting the Newest GRN
    List<GRN> newestGRN();
}
