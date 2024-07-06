package com.rafsunjani.wsd.ecommerce.repository;

import com.rafsunjani.wsd.ecommerce.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
