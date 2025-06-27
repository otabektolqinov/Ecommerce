package com.company.ecommerce.service.validation;

import com.company.ecommerce.domain.Product;
import com.company.ecommerce.domain.Users;
import com.company.ecommerce.repository.ProductRepository;
import com.company.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentValidation {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Product productExists(Long productId) {
        Optional<Product> optionalProduct = productRepository.findByIdAndDeletedAtIsNull(productId);
        return optionalProduct.orElse(null);
    }

    public Users userExists(Long userId) {
        Optional<Users> optionalUser = userRepository.findByIdAndDeletedAtIsNull(userId);
        return optionalUser.orElse(null);
    }
}
