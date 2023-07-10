package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Entity
@Data
@Table(name="DesignerGINSave")
public class DesignerGIN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;


    @Lob
    private Blob dbFile;

    private Date date = new Date();
}
