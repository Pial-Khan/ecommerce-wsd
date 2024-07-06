package com.rafsunjani.wsd.ecommerce.controller;

import com.rafsunjani.wsd.ecommerce.service.SaleDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class SaleDataControllerTest {

    @Mock
    private SaleDataService saleDataService;

    @InjectMocks
    private SaleDataController saleDataController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(saleDataController).build();
    }

    @Test
    public void testGetTotalSaleAmountForToday() throws Exception {
        when(saleDataService.getTotalSaleAmountForToday()).thenReturn(100L);

        mockMvc.perform(get("/api/sales/total-today"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(100)));

        verify(saleDataService, times(1)).getTotalSaleAmountForToday();
    }

    @Test
    public void testGetMaxSaleDayWithinRange() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2023-05-01");
        Date endDate = sdf.parse("2023-05-31");

        Map<String, Object> result = new HashMap<>();
        result.put("maxSaleDay", "2023-05-10");
        result.put("totalSale", 500L);

        when(saleDataService.getMaxSaleDayWithinRange(startDate, endDate)).thenReturn(result);

        mockMvc.perform(get("/api/sales/max-sale-day")
                        .param("startDate", "2023-05-01")
                        .param("endDate", "2023-05-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maxSaleDay", is("2023-05-10")))
                .andExpect(jsonPath("$.totalSale", is(500)));

        verify(saleDataService, times(1)).getMaxSaleDayWithinRange(startDate, endDate);
    }

    @Test
    public void testGetTopFiveSellingItems() throws Exception {
        List<Object[]> topSellingItems = Arrays.asList(
                new Object[]{"Product1", 100L},
                new Object[]{"Product2", 80L},
                new Object[]{"Product3", 60L},
                new Object[]{"Product4", 50L},
                new Object[]{"Product5", 40L}
        );

        when(saleDataService.getTopFiveSellingItems()).thenReturn(topSellingItems);

        mockMvc.perform(get("/api/sales/top-five-selling-items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName", is("Product1")))
                .andExpect(jsonPath("$[0].totalSale", is(100)))
                .andExpect(jsonPath("$[1].productName", is("Product2")))
                .andExpect(jsonPath("$[1].totalSale", is(80)))
                .andExpect(jsonPath("$[2].productName", is("Product3")))
                .andExpect(jsonPath("$[2].totalSale", is(60)))
                .andExpect(jsonPath("$[3].productName", is("Product4")))
                .andExpect(jsonPath("$[3].totalSale", is(50)))
                .andExpect(jsonPath("$[4].productName", is("Product5")))
                .andExpect(jsonPath("$[4].totalSale", is(40)));

        verify(saleDataService, times(1)).getTopFiveSellingItems();
    }

    @Test
    public void testGetTop5SellingItemsLastMonth() throws Exception {
        List<Object[]> topSellingItems = Arrays.asList(
                new Object[]{"Product1", 100L},
                new Object[]{"Product2", 80L},
                new Object[]{"Product3", 60L},
                new Object[]{"Product4", 50L},
                new Object[]{"Product5", 40L}
        );

        when(saleDataService.getTopFiveSellingItemsLastMonth()).thenReturn(topSellingItems);

        mockMvc.perform(get("/api/sales/top-five-selling-items-last-month"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName", is("Product1")))
                .andExpect(jsonPath("$[0].totalQuantity", is(100)))
                .andExpect(jsonPath("$[1].productName", is("Product2")))
                .andExpect(jsonPath("$[1].totalQuantity", is(80)))
                .andExpect(jsonPath("$[2].productName", is("Product3")))
                .andExpect(jsonPath("$[2].totalQuantity", is(60)))
                .andExpect(jsonPath("$[3].productName", is("Product4")))
                .andExpect(jsonPath("$[3].totalQuantity", is(50)))
                .andExpect(jsonPath("$[4].productName", is("Product5")))
                .andExpect(jsonPath("$[4].totalQuantity", is(40)));

        verify(saleDataService, times(1)).getTopFiveSellingItemsLastMonth();
    }
}
