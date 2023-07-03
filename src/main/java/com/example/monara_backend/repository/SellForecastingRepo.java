package com.example.monara_backend.repository;

import com.example.monara_backend.dto.PurchaseForecastingDto;
import com.example.monara_backend.dto.SellForecastingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellForecastingRepo extends JpaRepository<SellForecastingDto,Integer> {

    @Query(value =  "" +
            "SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS id, YEAR(date) AS purchase_year,"+
            "       MONTH(date) AS purchase_month,\n" +
            "       SUM(issued_quantity) AS total\n" +
            "FROM gin \n" +
            "GROUP BY purchase_year, purchase_month\n" +
            "ORDER BY purchase_year ASC, purchase_month ASC " +
            ";", nativeQuery = true)//Getting the Newest GIN
    List<SellForecastingDto> getMonthlyGIN();

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS id, YEAR(date) AS purchase_year,\n" +
            "       MONTH(date) AS purchase_month,\n" +
            "       SUM(issued_quantity) AS total\n" +
            "FROM gin\n" +
            "WHERE category_id = :category\n" +
            "GROUP BY purchase_year, purchase_month\n" +
            "ORDER BY purchase_year ASC, purchase_month ASC" +
            ";" , nativeQuery = true)//Getting the Newest GIN
    List<SellForecastingDto> getGinDataByMonthlyAndCategory(@Param("category")String category);

}
