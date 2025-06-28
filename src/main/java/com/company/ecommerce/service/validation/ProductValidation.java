package com.company.ecommerce.service.validation;

import com.company.ecommerce.domain.Category;
import com.company.ecommerce.domain.Product;
import com.company.ecommerce.domain.Seller;
import com.company.ecommerce.repository.CategoryRepository;
import com.company.ecommerce.repository.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductValidation {
    private final CategoryRepository categoryRepository;
    private final SellerRepository sellerRepository;

    public Category getCategoryOrThrow(Long id) {
        return categoryRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + id));
    }

    public Seller getSellerOrThrow(Long id) {
        return sellerRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found with ID: " + id));
    }

}
