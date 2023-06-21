package com.example.monara_backend.service;

import com.example.monara_backend.model.FileUpload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowroomService {
    public FileUpload create(FileUpload dbFile);
    public <dbFile> List<dbFile> viewAll();
    public <dbFile> dbFile viewById(long id);
}
