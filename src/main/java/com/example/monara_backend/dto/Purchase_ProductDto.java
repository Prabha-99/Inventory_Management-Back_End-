package com.example.monara_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Purchase_ProductDto {
    private String product_id;
    private String category_id;
    private String product_name;
    private String product_brand;
    private double product_price;
    private int product_quantity;
}
