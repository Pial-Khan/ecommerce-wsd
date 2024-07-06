package com.rafsunjani.wsd.ecommerce.repository;

import com.rafsunjani.wsd.ecommerce.domain.entity.SaleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDataRepository extends JpaRepository<SaleData, Long> {
}
