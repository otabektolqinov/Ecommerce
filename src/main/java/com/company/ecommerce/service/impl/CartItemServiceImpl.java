package com.company.ecommerce.service.impl;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.CartItemResponseDto;
import com.company.ecommerce.repository.UserRepository;
import com.company.ecommerce.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

//    private final CartItemRepository cartRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;

    @Override
    public HttpApiResponse<CartItemResponseDto> createCartItem(Long prodId, Integer quantity, Long userId) {
        return null;
    }

    @Override
    public HttpApiResponse<CartItemResponseDto> getAllCartItemsByUserId(Long userId) {
        return null;
    }

    @Override
    public HttpApiResponse<CartItemResponseDto> deleteCartItemById(Long userId) {
        return null;
    }
}
