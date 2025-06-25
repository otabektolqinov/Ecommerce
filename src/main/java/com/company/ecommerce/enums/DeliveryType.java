package com.company.ecommerce.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryType {

    IN_PERSON(0),
    DELIVERY(1);

    private final Integer index;

    public static DeliveryType fromValue(Integer index){
        for (DeliveryType deliveryType: DeliveryType.values()){
            if (deliveryType.getIndex().equals(index)){
                return deliveryType;
            }
        }
        return null;
    }
}
