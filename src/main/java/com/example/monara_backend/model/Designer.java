package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "showroom_file_document")
public class Designer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    private byte[] docfile;

    private String filename;


}
