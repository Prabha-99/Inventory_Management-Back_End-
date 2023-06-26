package com.example.monara_backend.dto;

import com.example.monara_backend.model.Designer;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ShowroomFileDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "docfile")
    private byte[] docfile;
}




