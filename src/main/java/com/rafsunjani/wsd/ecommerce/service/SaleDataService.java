package com.rafsunjani.wsd.ecommerce.service;

import com.rafsunjani.wsd.ecommerce.repository.SaleDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleDataService {

    private final SaleDataRepository saleDataRepository;

    public Long getTotalSaleAmountForToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfDay = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endOfDay = calendar.getTime();

        return saleDataRepository.findTotalSaleAmountForDay(startOfDay, endOfDay);
    }

    public Map<String, Object> getMaxSaleDayWithinRange(Date startDate, Date endDate) {
        Object[] result = saleDataRepository.findMaxSaleDayWithinRange(startDate, endDate);
        if (result != null && result.length > 0) {
            Map<String, Object> response = new HashMap<>();
            response.put("saleDate", result[0]);
            response.put("totalSale", result[1]);
            return response;
        }
        return null;
    }

    public List<Object[]> getTopFiveSellingItems() {
        return saleDataRepository.findTopFiveSellingItems().stream().limit(5).collect(Collectors.toList());
    }

}
