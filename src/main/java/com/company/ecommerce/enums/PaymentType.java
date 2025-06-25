package com.company.ecommerce.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {

    CASH(0),
    CARD(1),
    INSTALLMENTS(2);

    private final Integer index;

    public static PaymentType fromValue(Integer index){
        for (PaymentType paymentType: PaymentType.values()){
            if (paymentType.getIndex().equals(index)){
                return paymentType;
            }
        }
        return null;
    }

}
