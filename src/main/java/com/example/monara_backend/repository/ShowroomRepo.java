package com.example.monara_backend.repository;


import com.example.monara_backend.model.ShowroomFile;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ShowroomRepo extends JpaRepository<ShowroomFile,Integer> {

}
