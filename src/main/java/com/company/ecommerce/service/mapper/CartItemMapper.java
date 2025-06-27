package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.CartItem;
import com.company.ecommerce.dto.response.CartItemResponseDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CartItemMapper {

    @Mapping(target = "userId", expression = "java(item.getUsers().getId())")
    @Mapping(target = "productDto", expression = "java(productMapper.toResponseDto(item.getProduct()))")
    CartItemResponseDto toDto(CartItem item, @Context ProductMapper productMapper);

}
