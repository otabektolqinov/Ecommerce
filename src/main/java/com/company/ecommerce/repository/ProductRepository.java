package com.company.ecommerce.repository;

import com.company.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByIdAndDeletedAtIsNull(Long id);

    Optional<List<Product>> findAllByCategoryNameAndDeletedAtIsNull(String categoryName);
    @EntityGraph(
           attributePaths = {"comments","productFiles"}

    )
    Optional<List<Product>> findAllBySellerIdAndDeletedAtIsNull(Long id);
}
