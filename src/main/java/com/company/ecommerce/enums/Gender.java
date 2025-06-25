package com.company.ecommerce.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MALE(0),
    FEMALE(1);

    private final Integer index;

    public static Gender fromValue(Integer index){
        for (Gender gender: Gender.values()){
            if (gender.getIndex().equals(index)){
                return gender;
            }
        }
        return null;
    }
}
