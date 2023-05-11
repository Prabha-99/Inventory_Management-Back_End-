package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name="billsave")
public class BillSave {

   @Id

  // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bill_id;
    private String qu_no;
    private String st_date;
    private String end_date;
    private String cu_name;
    private String cu_address;
    private String cu_tele;
    private String other;
    private double total_amount;
    private String note;


}
