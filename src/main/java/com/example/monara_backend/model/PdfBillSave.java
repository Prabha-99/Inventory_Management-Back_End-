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

    @Column(name="bill_id")
    private Long bill_id;

    private String filename;

    private String filepath;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    private BillSave billSave;


    public PdfBillSave(String fileName, String filePath) {
        this.filename = fileName;
        this.filepath = filePath;
    }
}
