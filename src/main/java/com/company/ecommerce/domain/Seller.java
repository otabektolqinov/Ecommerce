package com.company.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Seller extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    private Integer orderCount;
    private Float rating;
    private Integer commentCount;
    private LocalDate registeredDate;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "seller"
    )
    private Set<SellerLogo> sellerLogo;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "seller"
    )
    private Set<Product> products;

}
