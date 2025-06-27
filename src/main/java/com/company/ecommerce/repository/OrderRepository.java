package com.company.ecommerce.repository;

import com.company.ecommerce.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByUsers_IdAndDeletedAtIsNull(Long userId);
}
