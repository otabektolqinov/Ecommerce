package com.company.ecommerce.dto;

import com.company.ecommerce.domain.Seller;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerLogoResponse {

    private String contentUrl;
    private String originalName;
    private String generatedName;
    private String mimeType;
    private Long size;
    private Long sellerId;
}
