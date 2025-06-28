package com.company.ecommerce.repository;

import com.company.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByIdAndDeletedAtIsNull(Long id);

    Page<Product> findAllByCategoryNameAndDeletedAtIsNull(String categoryName, Pageable pageable);

    @EntityGraph(
            attributePaths = {"comments", "productFiles"}

    )
    Page<Product> findAllBySellerIdAndDeletedAtIsNull(Long id, Pageable pageable);
}
