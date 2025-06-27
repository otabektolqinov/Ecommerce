package com.company.ecommerce.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRequestDto {

    private Long productId;
    private Integer quantity;
    private Long userId;

}
