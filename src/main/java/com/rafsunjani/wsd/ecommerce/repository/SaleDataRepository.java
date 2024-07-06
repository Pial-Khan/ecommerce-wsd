package com.rafsunjani.wsd.ecommerce.repository;

import com.rafsunjani.wsd.ecommerce.domain.entity.SaleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleDataRepository extends JpaRepository<SaleData, Long> {
    @Query("SELECT SUM(s.quantity * p.price) FROM SaleData s JOIN s.product p WHERE s.saleDate BETWEEN :startOfDay AND :endOfDay")
    Long findTotalSaleAmountForDay(@Param("startOfDay") Date startOfDay, @Param("endOfDay") Date endOfDay);

    @Query("SELECT s.saleDate, SUM(s.quantity * p.price) AS totalSale FROM SaleData s JOIN s.product p " +
            "WHERE s.saleDate BETWEEN :startDate AND :endDate " +
            "GROUP BY s.saleDate " +
            "ORDER BY totalSale DESC")
    Object[] findMaxSaleDayWithinRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT p.productName, SUM(s.quantity * p.price) AS totalSale FROM SaleData s JOIN s.product p " +
            "GROUP BY p.productName " +
            "ORDER BY totalSale DESC")
    List<Object[]> findTopFiveSellingItems();

}
