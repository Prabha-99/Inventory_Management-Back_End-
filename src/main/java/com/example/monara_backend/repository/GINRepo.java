package com.example.monara_backend.repository;

import com.example.monara_backend.model.GIN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GINRepo extends JpaRepository<GIN,Long> {
    @Query(value = "SELECT * FROM gin WHERE TIME(date) >= CURTIME() ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the Newest GIN
    List<GIN> newestGIN();

//    @Query(value = "SELECT path FROM reports WHERE report_name LIKE 'GIN%' ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the path of the Newest GIN
//    List<GIN> pathToNewestGIN();

    @Query(value = "SELECT report_name FROM reports WHERE report_name LIKE 'GIN%' ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the name of the Newest GIN
    String nameOFNewestGIN();

}

