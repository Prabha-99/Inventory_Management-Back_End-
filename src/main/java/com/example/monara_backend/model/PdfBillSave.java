package com.example.monara_backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="pdfbillsave")
public class PdfBillSave {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "filepath")
    private String filepath;



    public PdfBillSave(String fileName, String filePath) {
        this.filename = fileName;
        this.filepath = filePath;
    }
}
