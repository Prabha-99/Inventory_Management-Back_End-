package com.example.monara_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseOrderDto {
    private int purchaseOrder_id;
    private String seller_name;
    private String seller_address;
    private String category_name;
    private String product_name;
    private String product_brand;
    private double product_price;
    private int product_quantity;
}
