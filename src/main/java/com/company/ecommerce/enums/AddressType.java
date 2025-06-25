package com.company.ecommerce.enums;

import com.company.ecommerce.domain.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressType {

    USER(0),
    MARKET(1);

    private final Integer index;

    public static AddressType fromValue(Integer index){
        for (AddressType address: AddressType.values()){
            if (address.getIndex().equals(index)){
                return address;
            }
        }
        return null;
    }
}
