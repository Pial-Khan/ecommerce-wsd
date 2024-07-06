package com.rafsunjani.wsd.ecommerce.controller;

import com.rafsunjani.wsd.ecommerce.domain.entity.Customer;
import com.rafsunjani.wsd.ecommerce.domain.entity.WishList;
import com.rafsunjani.wsd.ecommerce.service.CustomerService;
import com.rafsunjani.wsd.ecommerce.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;
    private final CustomerService customerService;
    @GetMapping("/{customerUsername}")
    public List<WishList> getWishListByCustomerUsername(@PathVariable String customerUsername) {
        Customer customer = customerService.findByUsername(customerUsername);
        return wishListService.getWishListByCustomer(customer);
    }
}
