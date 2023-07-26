package com.example.monara_backend.repository;


import com.example.monara_backend.model.ShowroomFile;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowroomRepo extends JpaRepository<ShowroomFile,Integer> {

    @Query(value = "SELECT filename FROM showroom WHERE filename LIKE 'Archi%' ORDER BY date DESC LIMIT 1;", nativeQuery = true)//Getting the name of the Newest Stock Report
    String nameOFNewestArchi();

}
