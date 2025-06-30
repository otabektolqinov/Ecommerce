package com.company.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {

    @NotNull(message = "Delivery Address Id cannot be null")
    private Long deliveryAddressId;
    @PositiveOrZero(message = "Delivery Type Index should be 0 or 1")
    @NotNull(message = "Delivery Type Index cannot be null")
    private Integer deliveryTypeIndex;
    @NotNull(message = "UserId cannot be null")
    private Long userId;

}
