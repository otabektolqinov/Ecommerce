package com.company.ecommerce.domain;

import com.company.ecommerce.enums.DeliveryType;
import com.company.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    private Address deliveryAddress;

    @Enumerated(value = EnumType.STRING)
    private DeliveryType deliveryType;

    private LocalDateTime orderDate;
    private Double orderPrice;

    @OneToOne
    private Payment payment;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "orders"
    )
    private List<OrderItem> orderItems;

    @ManyToOne
    private Users users;

}
