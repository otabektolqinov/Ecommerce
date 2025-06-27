package com.company.ecommerce.service.validation;

import com.company.ecommerce.dto.ErrorDto;
import com.company.ecommerce.dto.request.CartItemRequestDto;
import com.company.ecommerce.repository.ProductRepository;
import com.company.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartItemValidation {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Optional<ErrorDto> validateCartItem(Long prodId, Long userId){
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            return Optional.of(new ErrorDto(
                    "/cart-item",
                    String.format("User with %d id is not found", userId),
                    HttpStatus.NOT_FOUND.value()
            ));
        }

        boolean productExists = productRepository.existsById(prodId);
        if (!productExists) {
            return Optional.of(new ErrorDto(
                    "/cart-item",
                    String.format("Product with %d id is not found", prodId),
                    HttpStatus.NOT_FOUND.value()
            ));
        }

        return Optional.empty();
    }

}
