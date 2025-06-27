package com.company.ecommerce.repository;

import com.company.ecommerce.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUsers_Id(Long usersId);

    void deleteAllByUsers_Id(Long usersId);
}
