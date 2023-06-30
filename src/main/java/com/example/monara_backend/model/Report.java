package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="reports")
public class Report {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long report_id;
    private String report_name;
    private String customer;
    private String path;
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        return sdf.format(this.date);
    }
}
