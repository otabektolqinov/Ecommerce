package com.company.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerRequestDto {
    private Long id;
    @NotBlank(message = "Seller name cannot be empty or null")
    private String name;

    @NotBlank(message = "PhoneNumber name cannot be empty or null")
    private String phoneNumber;

    @NotBlank(message = "Description name cannot be empty or null")
    private String description;

}
