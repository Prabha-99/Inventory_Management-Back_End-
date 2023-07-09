package com.example.monara_backend.service;

import com.example.monara_backend.dto.PurchaseForecastingDto;
import com.example.monara_backend.dto.SellForecastingDto;
import com.example.monara_backend.repository.SellForecastingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellForecastingService {
    @Autowired
    private SellForecastingRepo sellForecastingRepo;
    public List<SellForecastingDto> getAllGinData(String timeDuration, String category, String status) {

        List <SellForecastingDto> ginList = new ArrayList< >();

        try {
            if (timeDuration.equals("monthly") && category.equals("")) {
                ginList = sellForecastingRepo.getMonthlyGIN();
            } else if(timeDuration.equals("monthly") && !category.equals("")) {
                ginList = sellForecastingRepo.getGinDataByMonthlyAndCategory(category);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return ginList;
    }
}
