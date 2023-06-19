package com.example.monara_backend.repository;

import com.example.monara_backend.dto.ShowroomFileDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowroomRepo extends CrudRepository <ShowroomFileDocument,Long> {

    static ShowroomFileDocument findByFileName(String fileName) {
        return null;
    }
}
