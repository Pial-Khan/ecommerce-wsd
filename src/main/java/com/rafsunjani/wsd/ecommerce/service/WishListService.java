package com.rafsunjani.wsd.ecommerce.service;

import com.rafsunjani.wsd.ecommerce.domain.entity.WishList;
import com.rafsunjani.wsd.ecommerce.domain.entity.Customer;
import com.rafsunjani.wsd.ecommerce.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;

    public List<WishList> getWishListByCustomer(Customer customer) {
        return wishListRepository.findByCustomer(customer);
    }
}
