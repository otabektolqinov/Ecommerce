package com.company.ecommerce.dto.response;

import com.company.ecommerce.enums.AddressType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponseDto {

    private Long id;
    private String region;
    private String city;
    private String district;
    private String street;
    private String homeNumber;
    private Integer addressTypeIndex;

}
