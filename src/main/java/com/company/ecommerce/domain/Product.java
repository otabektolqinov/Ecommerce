package com.company.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    private Integer soldQuantity;
    private Float rating;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "product"
    )
    private Set<Comment> comments;


    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Category category;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "product"
    )
    private Set<ProductFile> productFiles;


}
