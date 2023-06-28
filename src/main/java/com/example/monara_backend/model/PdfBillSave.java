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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    @Column(name = "pdf_id")
    private Long pdf_id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "filepath")
    private String filepath;

    @OneToOne
    @MapsId
    @JoinColumn(name = "bill_id")
    private BillSave billSave;

    public PdfBillSave(String fileName, String filePath) {
        this.filename = fileName;
        this.filepath = filePath;
    }
}
