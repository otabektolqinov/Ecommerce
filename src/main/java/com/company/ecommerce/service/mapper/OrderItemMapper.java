package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.OrderItem;
import com.company.ecommerce.dto.response.OrderItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "prodName", expression = "java(item.getProduct().getName())")
    @Mapping(target = "prodId", expression = "java(item.getProduct().getId())")
    @Mapping(target = "priceAtOrder", source = "priceAtTime")
    OrderItemResponseDto toDto(OrderItem item);

}
