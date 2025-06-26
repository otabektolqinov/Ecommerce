package com.company.ecommerce.dto.response;

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
