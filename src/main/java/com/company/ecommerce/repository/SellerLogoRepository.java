package com.company.ecommerce.repository;

import com.company.ecommerce.domain.SellerLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

@Repository
public interface SellerLogoRepository extends JpaRepository<SellerLogo, Long> {

    Optional<SellerLogo> findByIdAndDeletedAtIsNull(Long id);

    Set<SellerLogo> findAllBySellerIdAndDeletedAtIsNull(Long sellerId);
}
