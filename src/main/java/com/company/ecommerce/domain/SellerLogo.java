package com.company.ecommerce.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SellerLogo extends BaseEntity{

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contentUrl;

    private String originalName;
    private String generatedName;
    private String mimeType;
    private Long size;

    @OneToOne
    private Seller seller;

}
