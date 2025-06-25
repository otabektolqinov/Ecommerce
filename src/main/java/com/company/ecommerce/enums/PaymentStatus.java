package com.company.ecommerce.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {

    PENDING(0),
    COMPLETED(1),
    FAILED(2);
    private final Integer index;

    public static PaymentStatus fromValue(Integer index){
        for (PaymentStatus paymentStatus: PaymentStatus.values()){
            if (paymentStatus.getIndex().equals(index)){
                return paymentStatus;
            }
        }
        return null;
    }
}
