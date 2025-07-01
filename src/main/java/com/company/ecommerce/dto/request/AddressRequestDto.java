package com.company.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequestDto {

    @NotBlank(message = "Region cannot be blank, empty or null")
    private String region;
    @NotBlank(message = "City cannot be blank, empty or null")
    private String city;
    @NotBlank(message = "District cannot be blank, empty or null")
    private String district;
    @NotBlank(message = "Street cannot be blank, empty or null")
    private String street;
    @NotBlank(message = "HouseNumber cannot be blank, empty or null")
    private String homeNumber;
    @NotNull(message = "AddressType cannot be null")
    @PositiveOrZero(message = "Address Type Index should be 0 or 1")
    private Integer addressTypeIndex;

}
