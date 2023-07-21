package com.example.monara_backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "products")
public class Product {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id" ,insertable=false, updatable=false)
    private Category category;
    private String category_id;
    private String product_brand;
    private String product_name;
    private double product_price;
    private int product_quantity;

}
