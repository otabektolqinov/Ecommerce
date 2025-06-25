package com.company.ecommerce.dto;

import com.company.ecommerce.domain.Product;
import com.company.ecommerce.domain.SellerLogo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerResponse {

    private String name;
    private String phoneNumber;
    private String description;
    private Integer orderCount;
    private Float rating;
    private Integer commentCount;
    private LocalDate registeredDate;
    private SellerLogo sellerLogo;
    private List<Product> products;
}
