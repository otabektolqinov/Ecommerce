package com.company.ecommerce.dto.response;

import com.company.ecommerce.domain.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductFileResponseDto {
    private String contentUrl;

}
