package com.example.monara_backend.service;

import com.example.monara_backend.dto.ForecastingDto;
import com.example.monara_backend.model.GRN;
import com.example.monara_backend.repository.ForecastingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ForecastingService {
    @Autowired
    private ForecastingRepo forecastingRepo;

    public List<ForecastingDto> getAllGrnData(String timeDuration, String category, String status) {

        List<ForecastingDto> grnList = new ArrayList<>();

        try{
            if(
                            timeDuration.equals("monthly")
                            && category.equals("")
                            && status.equals("")
            ) {

                grnList = forecastingRepo.getMonthlyGRN();
//                System.out.println("TYPE: " + grnList.getClass());
               // grnList.add(new ForecastingDto(1,2022,1,2));
            }
        } catch(Exception exception) {
            System.out.println(exception);
        }
        return  grnList;
    }
}
