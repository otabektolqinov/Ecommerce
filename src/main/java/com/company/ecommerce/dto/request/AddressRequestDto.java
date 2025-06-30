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

    @NotNull(message = "Region cannot be null")
    @NotBlank(message = "Region cannot be blank")
    private String region;
    @NotNull(message = "City cannot be null")
    @NotBlank(message = "City cannot be blank")
    private String city;
    @NotNull(message = "District cannot be null")
    @NotBlank(message = "District cannot be blank")
    private String district;
    @NotNull(message = "Street cannot be null")
    @NotBlank(message = "Street cannot be blank")
    private String street;
    @NotNull(message = "HouseNumber cannot be null")
    @NotBlank(message = "HouseNumber cannot be blank")
    private String homeNumber;
    @NotNull(message = "AddressType cannot be null")
    @PositiveOrZero(message = "Address Type Index should be 0 or 1")
    private Integer addressTypeIndex;

}
