package com.example.monara_backend.repository;

import com.example.monara_backend.dto.ForecastingDto;
import com.example.monara_backend.model.GIN;
import com.example.monara_backend.model.GRN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastingRepo extends JpaRepository<ForecastingDto,Integer>{
    // search methods
    // time,category,status

    // get by time
    // select * from grn where

    @Query(value = "" +
            "SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS id, YEAR(date) AS purchase_year,"+
            "       MONTH(date) AS purchase_month,\n" +
            "       COUNT(*) AS total_purchases\n" +
            "FROM grn \n" +
            "GROUP BY purchase_year, purchase_month\n" +
            "ORDER BY purchase_year ASC, purchase_month ASC " +
            ";", nativeQuery = true)//Getting the Newest GIN
    List<ForecastingDto> getMonthlyGRN();

//    String sqlQuery = "SELECT YEAR(date) AS purchase_year, \n" +
//            "       MONTH(date) AS purchase_month, \n" +
//            "       COUNT(*) AS total_purchases \n" +
//            "FROM grn \n" +
//            "GROUP BY purchase_year, purchase_month \n" +
//            "ORDER BY purchase_year ASC, purchase_month ASC \n" +
//            ";";
//
//    @Query(value = "SELECT YEAR('date') AS purchase_year,\n" +
//            "       MONTH(date) AS purchase_month,\n" +
//            "       COUNT(*) AS total_purchases\n" +
//            "FROM grn\n" +
//            "GROUP BY purchase_year, purchase_month\n" +
//            "ORDER BY purchase_year ASC, purchase_month ASC;")
//    List<ForecastingDto> getMonthlyGRN();

}
