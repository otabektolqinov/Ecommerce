package com.company.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem extends BaseEntity{
    @OneToOne
    private Product product;
    private Integer quantity;

    @ManyToOne
    private Users users;

}
