package com.company.ecommerce.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {

    private Long deliveryAddressId;
    private Integer deliveryTypeIndex;
    private Long userId;

}
