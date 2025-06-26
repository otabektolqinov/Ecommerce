package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.CartItemResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {

    HttpApiResponse<CartItemResponseDto> createCartItem(Long prodId, Integer quantity, Long userId);
    HttpApiResponse<CartItemResponseDto> getAllCartItemsByUserId(Long userId);
    HttpApiResponse<CartItemResponseDto> deleteCartItemById(Long userId);

}
