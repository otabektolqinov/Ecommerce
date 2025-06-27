package com.company.ecommerce.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Long id;
    private Integer orderStatusIndex;
    private AddressResponseDto address;
    private Integer deliveryTypeIndex;
    private LocalDateTime orderDate;
    private Double orderPrice;
    private PaymentResponseDto payment;
    private List<OrderItemResponseDto> orderItems;

}
