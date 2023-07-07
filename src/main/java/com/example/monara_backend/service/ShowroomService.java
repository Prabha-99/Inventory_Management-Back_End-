package com.example.monara_backend.service;

import com.example.monara_backend.model.ShowroomFile;
import com.example.monara_backend.repository.ShowroomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowroomService {
    @Autowired
    private ShowroomRepo showroomRepo;


    public ShowroomFile saveDetails(ShowroomFile showroomFile) {

        return showroomRepo.save(showroomFile);
    }
    public ShowroomFile getFileById(Integer id) {
        return showroomRepo.findById(id).orElse(null);
    }

    public List<ShowroomFile> getAllFiles() {
        return showroomRepo.findAll();
    }
}
