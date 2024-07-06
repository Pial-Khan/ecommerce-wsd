package com.rafsunjani.wsd.ecommerce.service;

import com.rafsunjani.wsd.ecommerce.repository.SaleDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaleDataServiceTest {

    @Mock
    private SaleDataRepository saleDataRepository;

    @InjectMocks
    private SaleDataService saleDataService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testGetTotalSaleAmountForToday() {
        Long expectedTotalSaleAmount = 100L;

        when(saleDataRepository.findTotalSaleAmountForDay(any(Date.class), any(Date.class))).thenReturn(expectedTotalSaleAmount);

        Long totalSaleAmountForToday = saleDataService.getTotalSaleAmountForToday();

        assertEquals(expectedTotalSaleAmount, totalSaleAmountForToday);
    }

    @Test
    public void testGetMaxSaleDayWithinRange() {
        Date startDate = new Date();
        Date endDate = new Date();
        Object[] result = { new Date(), 200L };

        when(saleDataRepository.findMaxSaleDayWithinRange(startDate, endDate)).thenReturn(result);

        Map<String, Object> response = saleDataService.getMaxSaleDayWithinRange(startDate, endDate);

        assertEquals(result[0], response.get("saleDate"));
        assertEquals(result[1], response.get("totalSale"));
    }

    @Test
    public void testGetTopFiveSellingItems() {
        List<Object[]> topSellingItems = new ArrayList<>();
        topSellingItems.add(new Object[]{"Product1", 10});
        topSellingItems.add(new Object[]{"Product2", 8});

        when(saleDataRepository.findTopFiveSellingItems()).thenReturn(topSellingItems);

        List<Object[]> result = saleDataService.getTopFiveSellingItems();

        assertEquals(topSellingItems.size(), result.size());
        assertEquals(topSellingItems.get(0)[0], result.get(0)[0]);
        assertEquals(topSellingItems.get(0)[1], result.get(0)[1]);
    }

    @Test
    public void testGetTopFiveSellingItemsLastMonth() {
        List<Object[]> topSellingItems = new ArrayList<>();
        topSellingItems.add(new Object[]{"Product1", 10});
        topSellingItems.add(new Object[]{"Product2", 8});

        when(saleDataRepository.findTopFiveSellingItemsLastMonth(any(Date.class), any(Date.class))).thenReturn(topSellingItems);

        List<Object[]> result = saleDataService.getTopFiveSellingItemsLastMonth();

        assertEquals(topSellingItems.size(), result.size());
        assertEquals(topSellingItems.get(0)[0], result.get(0)[0]);
        assertEquals(topSellingItems.get(0)[1], result.get(0)[1]);
    }
}
