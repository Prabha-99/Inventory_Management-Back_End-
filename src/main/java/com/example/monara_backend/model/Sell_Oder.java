package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "sell_orders")
public class Sell_Oder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int sellOrder_id;
    private String customer_name;
    private String customer_address;
    private String category_name;
    private String product_name;
    private String product_brand;
    private double product_price;
    private int product_quantity;
}
