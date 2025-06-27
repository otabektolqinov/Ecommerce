package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.CartItemResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {

    HttpApiResponse<CartItemResponseDto> createCartItem(Long prodId, Integer quantity, Long userId);
    HttpApiResponse<List<CartItemResponseDto>> getAllCartItemsByUserId(Long userId);
    HttpApiResponse<CartItemResponseDto> deleteCartItemById(Long userId);
    HttpApiResponse<CartItemResponseDto> changeCartItemCountById(Long itemId, Integer count);

}
