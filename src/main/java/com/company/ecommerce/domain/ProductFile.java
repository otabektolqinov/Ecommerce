package com.company.ecommerce.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductFile extends BaseEntity {

    private String contentUrl;
    private String originalName;
    private String generatedName;
    private String mimeType;
    private Long size;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

}
