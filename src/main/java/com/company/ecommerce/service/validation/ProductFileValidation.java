package com.company.ecommerce.service.validation;

import com.company.ecommerce.domain.Product;
import com.company.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductFileValidation {
    private final ProductRepository productRepository;

    public Product productExists(Long productId) {
        Optional<Product> optionalProduct = productRepository.findByIdAndDeletedAtIsNull(productId);
        return optionalProduct.orElse(null);
    }
}
