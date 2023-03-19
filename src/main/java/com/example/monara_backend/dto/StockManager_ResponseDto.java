package com.example.monara_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data

public class StockManager_ResponseDto {
    private String code;
    private String message;
    private Object content;
}
