package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name="billsave")
public class BillSave {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bill_id;
    private String qu_no;
    private Date st_date;
    private Date end_date;
    private String cu_name;
    private String cu_address;
    private String cu_tele;
    private String other;
    private double quantity;
    private double unit_price;
    private double amount;
    private double discount;
    private double subamount;
    private double total_amount;
    private String note;
    private double vat;



}
