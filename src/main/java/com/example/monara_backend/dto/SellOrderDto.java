package com.example.monara_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellOrderDto {
    private int sellOrder_id;
    private String customer_name;
    private String customer_address;
    private String category_name;
    private String product_name;
    private String product_brand;
    private double product_price;
    private int product_quantity;
}
