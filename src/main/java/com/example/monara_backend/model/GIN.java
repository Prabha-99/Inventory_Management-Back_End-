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
@Table(name = "GIN")
public class GIN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long invoice_no;
    private Date date;
    private String customer_name;
    private String address;
    private String contact_nu;
    private String item_description;
    private int invoiced_quantity;
    private int issued_quantity;
    private String remarks;

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        return sdf.format(this.date);
    }

}
