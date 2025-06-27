package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.CartItemResponseDto;
import com.company.ecommerce.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-item")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<CartItemResponseDto>> createCartItem(
            @RequestParam("productId") Long prodId,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("userId") Long userId
    ){
        HttpApiResponse<CartItemResponseDto> response = cartItemService.createCartItem(prodId, quantity, userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<HttpApiResponse<List<CartItemResponseDto>>> getAllCartItemsByUserId(
            @RequestParam("userId") Long userId
    ){
        HttpApiResponse<List<CartItemResponseDto>> response = cartItemService.getAllCartItemsByUserId(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpApiResponse<CartItemResponseDto>> deleteCartItemById(
            @RequestParam("userId") Long cartId
    ){
        HttpApiResponse<CartItemResponseDto> response = cartItemService.deleteCartItemById(cartId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
