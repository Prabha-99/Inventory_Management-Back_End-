package com.example.monara_backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "products")
public class Product {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    private String category_id;
    private String product_brand;
    private String product_name;
    private double product_price;
    private int product_quantity;
}
