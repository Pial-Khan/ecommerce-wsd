package com.rafsunjani.wsd.ecommerce.repository;

import com.rafsunjani.wsd.ecommerce.domain.entity.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long> {
}
