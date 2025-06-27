package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Orders;
import com.company.ecommerce.dto.response.OrderResponseDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
    OrderItemMapper itemMapper = Mappers.getMapper(OrderItemMapper.class);

    @Named("toDto")
    @Mapping(target = "orderStatusIndex", expression = "java(orders.getOrderStatus().getStatusId())")
    @Mapping(target = "deliveryTypeIndex", expression = "java(orders.getDeliveryType().getIndex())")
    @Mapping(target = "address", expression = "java(addressMapper.toDto(orders.getDeliveryAddress()))")
    @Mapping(target = "orderItems", expression = "java(itemMapper.toDtoList(orders.getOrderItems()))")
    OrderResponseDto toDto(Orders orders);

    @IterableMapping(qualifiedByName = "toDto")
    List<OrderResponseDto> toDtoList(List<Orders> orders);

}
