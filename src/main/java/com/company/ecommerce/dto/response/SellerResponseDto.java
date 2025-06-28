package com.company.ecommerce.dto.response;

import com.company.ecommerce.domain.SellerLogo;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerResponseDto {

    private String name;
    private String phoneNumber;
    private String description;
    private Integer orderCount;
    private Float rating;
    private Integer commentCount;
    private LocalDate registeredDate;
    private Set<SellerLogoResponse> sellerLogo;
    private Set<ProductResponseDto> products;
}
