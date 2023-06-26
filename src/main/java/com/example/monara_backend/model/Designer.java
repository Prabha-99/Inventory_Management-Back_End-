package com.example.monara_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name="showroom")
public class Designer {
    @Id
    private int id;
    private String date;
    private byte[] db_file;
    private String name;

}



