package com.example.monara_backend.service;

import com.example.monara_backend.model.FileUpload;
import com.example.monara_backend.repository.ShowroomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowroomServiceImpl implements ShowroomService{

    private ShowroomRepo showroomRepo;


    public FileUpload create(FileUpload dbFile) {

        return showroomRepo.save(dbFile);
    }

    public List<FileUpload> viewAll() {

        return (List<FileUpload>) showroomRepo.findAll();
    }

    public FileUpload viewById(long id) {

        return showroomRepo.findById(id).get();
    }

}
