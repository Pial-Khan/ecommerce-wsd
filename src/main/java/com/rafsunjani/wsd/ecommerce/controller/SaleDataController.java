package com.rafsunjani.wsd.ecommerce.controller;

import com.rafsunjani.wsd.ecommerce.service.SaleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleDataController {

    private static final Logger logger = LogManager.getLogger(SaleDataService.class);

    private final SaleDataService saleDataService;

    @GetMapping("/total-today")
    public Long getTotalSaleAmountForToday() {
        return saleDataService.getTotalSaleAmountForToday();
    }

    @GetMapping("/max-sale-day")
    public Map<String, Object> getMaxSaleDayWithinRange(
            @RequestParam String startDate,
            @RequestParam String endDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);

        return saleDataService.getMaxSaleDayWithinRange(start, end);
    }
    @GetMapping("/top-five-selling-items")
    public List<Map<String, Object>> getTopFiveSellingItems() {
        return saleDataService.getTopFiveSellingItems().stream().map(result -> {
            Map<String, Object> item = new HashMap<>();
            item.put("productName", result[0]);
            item.put("totalSale", result[1]);
            return item;
        }).collect(Collectors.toList());
    }
    @GetMapping("/top-five-selling-items-last-month")
    public List<Map<String, Object>> getTop5SellingItemsLastMonth() {
        logger.info("API call to get top 5 selling items for the last month.");

        List<Map<String, Object>> topSellingItems = saleDataService.getTopFiveSellingItemsLastMonth().stream().map(result -> {
            Map<String, Object> item = new HashMap<>();
            item.put("productName", result[0]);
            item.put("totalQuantity", result[1]);
            return item;
        }).collect(Collectors.toList());

        logger.debug("Top 5 selling items for the last month response: {}", topSellingItems);

        return topSellingItems;
    }
}
