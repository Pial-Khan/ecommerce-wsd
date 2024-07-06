package com.rafsunjani.wsd.ecommerce.repository;

import com.rafsunjani.wsd.ecommerce.domain.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
}
