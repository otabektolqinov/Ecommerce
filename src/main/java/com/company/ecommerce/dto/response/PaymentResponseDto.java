package com.company.ecommerce.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {

    private Long paymentId;
    private Integer paymentTypeIndex;
    private Integer paymentStatusIndex;
    private LocalDateTime paymentDate;
    private String transactionId;
    private Long orderId;
    private Double paymentAmount;

}
