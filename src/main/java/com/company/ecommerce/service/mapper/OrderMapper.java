package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Orders;
import com.company.ecommerce.dto.response.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "orderStatusIndex", expression = "java(orders.getOrderStatus().getStatusId())")
    @Mapping(target = "deliveryTypeIndex", expression = "java(orders.getDeliveryType().getIndex())")
    @Mapping(target = "address", expression = "java(addressMapper.toDto(orders.getDeliveryAddress()))")
    OrderResponseDto toDto(Orders orders);
}
