package com.rafsunjani.wsd.ecommerce.service;

import com.rafsunjani.wsd.ecommerce.domain.entity.Customer;
import com.rafsunjani.wsd.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
