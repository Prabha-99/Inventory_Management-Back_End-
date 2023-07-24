package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Entity
@Data
@Table(name = "DesignerBill")
public class DesignerBillSend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;

    private String customerName;

    private String filePath;

    private Date date = new Date();
}
