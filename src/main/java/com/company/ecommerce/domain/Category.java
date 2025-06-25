package com.company.ecommerce.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category extends BaseEntity {

    private String name;
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private List<Product> products;

}
