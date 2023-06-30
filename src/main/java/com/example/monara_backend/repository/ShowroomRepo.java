package com.example.monara_backend.repository;

import com.example.monara_backend.model.ShowroomFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowroomRepo extends CrudRepository<ShowroomFile, Long> {

}

