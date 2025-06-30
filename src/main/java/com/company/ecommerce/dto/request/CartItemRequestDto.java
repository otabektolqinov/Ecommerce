package com.company.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRequestDto {

    @NotNull(message = "ProductId cannot be null")
    private Long productId;
    @PositiveOrZero(message = "Quantity cannot be negative number")
    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;
    @NotNull(message = "UserId cannot be null")
    private Long userId;

}
