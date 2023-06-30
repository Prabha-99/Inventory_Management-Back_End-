package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "purchase_orders")
public class Purchase_Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int purchaseOrder_id;
    private String seller_name;
    private String seller_address;
    private String category_name;
    private String product_name;
    private String product_brand;
    private double product_price;
    private int product_quantity;
}
