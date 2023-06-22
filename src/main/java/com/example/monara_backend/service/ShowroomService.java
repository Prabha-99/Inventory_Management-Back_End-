package com.example.monara_backend.service;

import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.repository.ShowroomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowroomService {
    @Autowired
    private ShowroomRepo showroomRepo;


    public ShowroomFile create(ShowroomFile dbFile) {

        return showroomRepo.save(dbFile);
    }
}
