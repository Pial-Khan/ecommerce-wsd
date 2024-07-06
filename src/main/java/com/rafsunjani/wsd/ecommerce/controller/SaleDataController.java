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
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleDataController {

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
}
