package com.example.monara_backend.service;

import com.example.monara_backend.model.FileUpload;
import com.example.monara_backend.repository.ShowroomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowroomServiceImpl implements ShowroomService{
    @Autowired
    private ShowroomRepo showroomRepo;

    @Override
    public FileUpload create(FileUpload dbFile) {

        return showroomRepo.save(dbFile);
    }

    @Override
    public List<FileUpload> viewAll() {

        return (List<FileUpload>) showroomRepo.findAll();
    }

    @Override
    public FileUpload viewById(long id) {

        return showroomRepo.findById(id).get();
    }

}
