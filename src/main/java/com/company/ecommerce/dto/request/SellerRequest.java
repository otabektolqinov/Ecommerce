package com.company.ecommerce.dto.request;

import com.company.ecommerce.domain.SellerLogo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerRequest {
    private Long id;
    private String name;
    private String phoneNumber;
    private String description;
    private SellerLogo sellerLogo;

}
