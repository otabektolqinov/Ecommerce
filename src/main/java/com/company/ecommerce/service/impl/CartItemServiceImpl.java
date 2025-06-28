package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.CartItem;
import com.company.ecommerce.domain.Product;
import com.company.ecommerce.domain.Users;
import com.company.ecommerce.dto.ErrorDto;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.CartItemResponseDto;
import com.company.ecommerce.repository.CartItemRepository;
import com.company.ecommerce.repository.ProductRepository;
import com.company.ecommerce.repository.UserRepository;
import com.company.ecommerce.service.CartItemService;
import com.company.ecommerce.service.mapper.CartItemMapper;
import com.company.ecommerce.service.mapper.ProductMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import com.company.ecommerce.service.validation.CartItemValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemValidation cartValidation;
    private final ProductMapper productMapper;
    private final CartItemMapper cartMapper;

    @Override
    public HttpApiResponse<CartItemResponseDto> createCartItem(Long prodId, Integer quantity, Long userId) {
        Optional<ErrorDto> errorDto = cartValidation.validateCartItem(prodId, userId, quantity);
        if (!errorDto.isEmpty()) {
            return HttpApiResponse.<CartItemResponseDto>builder()
                    .errors(errorDto.get())
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
        }

        Optional<Users> usersOptional = userRepository.findByIdAndDeletedAtIsNull(userId);
        Optional<Product> optionalProduct = productRepository.findByIdAndDeletedAtIsNull(prodId);
        CartItem item = new CartItem(optionalProduct.get(), quantity, usersOptional.get());

        CartItem cartItem = cartRepository.save(item);

        CartItemResponseDto content = new CartItemResponseDto(
                productMapper.toResponseDto(optionalProduct.get()),
                cartItem.getQuantity(),
                cartItem.getUsers().getId()
        );

        return HttpApiResponse.<CartItemResponseDto>builder()
                .success(true)
                .message("Successfully Added Cart Item")
                .status(HttpStatus.CREATED)
                .responseCode(HttpStatus.CREATED.value())
                .content(content)
                .build();
    }

    @Override
    public HttpApiResponse<List<CartItemResponseDto>> getAllCartItemsByUserId(Long userId) {
        List<CartItem> allByUsersId = cartRepository.findAllByUsers_Id(userId);
        if (allByUsersId.isEmpty()){
            return HttpApiResponse.<List<CartItemResponseDto>>builder()
                    .message("Cart is empty")
                    .responseCode(HttpStatus.NO_CONTENT.value())
                    .status(HttpStatus.NO_CONTENT)
                    .success(true)
                    .build();
        }

        return HttpApiResponse.<List<CartItemResponseDto>>builder()
                .success(true)
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .message("OK")
                .content(allByUsersId.stream().map(cartItem -> cartMapper.toDto(cartItem, productMapper)).toList())
                .build();
    }


    @Override
    public HttpApiResponse<CartItemResponseDto> deleteCartItemById(Long userId) {
        cartRepository.deleteAllByUsers_Id(userId);

        return HttpApiResponse.<CartItemResponseDto>builder()
                .message("All items in the card removed")
                .responseCode(HttpStatus.OK.value())
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public HttpApiResponse<CartItemResponseDto> changeCartItemCountById(Long itemId, Integer count) {
        Optional<CartItem> itemOptional = cartRepository.findById(itemId);
        if (itemOptional.isEmpty())
            return ResponseUtils.buildNotFoundResponse("CartItem", itemId);

        itemOptional.get().setQuantity(count);

        CartItem saved = cartRepository.save(itemOptional.get());

        return HttpApiResponse.<CartItemResponseDto>builder()
                .responseCode(HttpStatus.OK.value())
                .success(true)
                .content(cartMapper.toDto(saved, productMapper))
                .message("OK")
                .status(HttpStatus.OK)
                .build();
    }
}
