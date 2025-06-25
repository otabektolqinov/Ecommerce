package com.company.ecommerce.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    NEW(0),
    ONTHEWAY(1),
    ARRIVED(2),
    WAITING(3),
    GIVEN(4),
    CANCELED(5);

    private final Integer statusId;

    public static OrderStatus fromValue(Integer index){
        for (OrderStatus status: OrderStatus.values()){
            if (status.getStatusId().equals(index)){
                return status;
            }
        }
        return null;
    }

}
