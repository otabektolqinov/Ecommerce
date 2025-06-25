package com.company.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderItem extends BaseEntity {

    @OneToOne(
            cascade = CascadeType.ALL
    )
    private Product product;

    private Integer quantity;
    private Double priceAtTime;

    @ManyToOne
    private Orders orders;

}
