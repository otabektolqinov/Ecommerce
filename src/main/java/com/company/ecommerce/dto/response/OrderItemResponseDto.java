package com.company.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponseDto {

    private Long prodId;
    private String prodName;
    private Integer quantity;
    private Double priceAtOrder;

}
