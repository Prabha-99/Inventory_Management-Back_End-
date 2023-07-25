package com.example.monara_backend.repository;

import com.example.monara_backend.dto.PurchaseForecastingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseForecastingRepo extends JpaRepository<PurchaseForecastingDto,Integer>{

    //Get purchasing details for all categories
    @Query(value = "" +
            "SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS id, YEAR(date) AS purchase_year,"+
            "       MONTH(date) AS purchase_month,\n" +
            "       SUM(received_quantity) AS total\n" +
            "FROM grn \n" +
            "GROUP BY purchase_year, purchase_month\n" +
            "ORDER BY purchase_year ASC, purchase_month ASC " +
            ";", nativeQuery = true)//Getting the Newest GRN
    List<PurchaseForecastingDto> getMonthlyGRN();


    //Get purchasing details for Each category
    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS id, YEAR(date) AS purchase_year,\n" +
            "       MONTH(date) AS purchase_month,\n" +
            "       SUM(received_quantity) AS total\n" +
            "FROM grn\n" +
            "WHERE category_id = :category\n" +
            "GROUP BY purchase_year, purchase_month\n" +
            "ORDER BY purchase_year ASC, purchase_month ASC" +
            ";" , nativeQuery = true)//Getting the Newest GIN
    List<PurchaseForecastingDto> getGrnDataByMonthlyAndCategory(@Param("category")String category);




}
