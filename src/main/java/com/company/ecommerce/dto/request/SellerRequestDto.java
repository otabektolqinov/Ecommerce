package com.company.ecommerce.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerRequestDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String description;

}
