package com.company.ecommerce.domain;

import com.company.ecommerce.enums.PaymentStatus;
import com.company.ecommerce.enums.PaymentType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String transactionId;

    @OneToOne
    private Orders orders;

}
