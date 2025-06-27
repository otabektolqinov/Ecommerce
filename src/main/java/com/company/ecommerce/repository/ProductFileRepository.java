package com.company.ecommerce.repository;

import com.company.ecommerce.domain.ProductFile;
import com.company.ecommerce.dto.response.ProductFileResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {
    Optional<ProductFile> findProductFileByIdAndDeletedAtIsNull(Long prodId);


}
