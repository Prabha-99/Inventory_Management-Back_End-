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
@Table(name = "GRN")
public class GRN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category_id;
    private Long invoice_no;
    private Date date;
    private String supplier_name;
    private String address;
    private String contact_nu;
    private String item_description;
    private int ordered_quantity;
    private int received_quantity;
    private String remarks;

    public void setDate(Date date) {
        this.date = new Date();
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        return sdf.format(this.date);
    }
}
