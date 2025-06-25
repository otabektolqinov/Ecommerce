package com.company.ecommerce.domain;

import com.company.ecommerce.enums.AddressType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address extends BaseEntity {

    private String region;
    private String city;
    private String district;
    private String street;
    private String homeNumber;

    @Enumerated(value = EnumType.STRING)
    private AddressType addressType;

}
