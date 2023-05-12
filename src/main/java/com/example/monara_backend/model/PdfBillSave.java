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

    @Lob
    @Column(name = "content", columnDefinition = "LONGBLOB")
    private byte[] content;

}
