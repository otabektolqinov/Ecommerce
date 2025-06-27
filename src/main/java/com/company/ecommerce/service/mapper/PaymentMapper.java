package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Payment;
import com.company.ecommerce.dto.response.PaymentResponseDto;
import com.company.ecommerce.enums.PaymentStatus;
import com.company.ecommerce.enums.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PaymentType.class, PaymentStatus.class})
public interface PaymentMapper {

    @Mapping(target = "paymentTypeIndex", expression = "java(payment.getPaymentType().getIndex())")
    @Mapping(target = "paymentStatusIndex", expression = "java(payment.getPaymentStatus().getIndex())")
    @Mapping(target = "paymentId", expression = "java(payment.getId())")
    @Mapping(target = "paymentDate", expression = "java(payment.getCreatedAt())")
    @Mapping(target = "paymentAmount", expression = "java(payment.getOrders().getOrderPrice())")
    @Mapping(target = "orderId", expression = "java(payment.getOrders().getId())")
    PaymentResponseDto toDto(Payment payment);

}
