package com.company.ecommerce.service.validation;

import com.company.ecommerce.domain.Category;
import com.company.ecommerce.domain.Product;
import com.company.ecommerce.domain.Seller;
import com.company.ecommerce.repository.CategoryRepository;
import com.company.ecommerce.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductValidation {
    private final CategoryRepository categoryRepository;
    private final SellerRepository sellerRepository;

    public boolean categoryExist(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndDeletedAtIsNull(id);
        return optionalCategory.isPresent();
    }

    public boolean sellerExist(Long id) {
        Optional<Seller> optionalSeller = sellerRepository.findByIdAndDeletedAtIsNull(id);
        return optionalSeller.isPresent();
    }
}
