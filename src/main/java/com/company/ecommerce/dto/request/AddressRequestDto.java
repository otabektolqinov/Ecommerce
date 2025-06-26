package com.company.ecommerce.dto.request;

import com.company.ecommerce.enums.AddressType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequestDto {

    private String region;
    private String city;
    private String district;
    private String street;
    private String homeNumber;
    private Integer addressTypeIndex;

}
