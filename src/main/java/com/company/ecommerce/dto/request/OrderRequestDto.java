package com.company.ecommerce.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {

    private Integer orderStatusIndex;
    private Integer deliveryAddressId;
    private Integer deliveryTypeIndex;

}
