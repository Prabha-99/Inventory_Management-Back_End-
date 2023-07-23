package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;


@Entity
@Data
@Table(name = "showroom")
public class ShowroomFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String filename;


    private String filePath;

    private Date date = new Date();


}

