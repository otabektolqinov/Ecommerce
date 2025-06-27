package com.company.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponseDto {

    private ProductResponseDto productDto;
    private Integer quantity;
    private Long userId;

}
