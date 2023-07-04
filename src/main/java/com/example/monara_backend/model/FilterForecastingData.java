package com.example.monara_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilterForecastingData {
    private String timeDuration;
    private String category;
    private String status;
}
