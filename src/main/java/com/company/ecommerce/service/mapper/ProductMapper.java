package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Product;
import com.company.ecommerce.dto.request.ProductRequestDto;
import com.company.ecommerce.dto.response.ProductResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "productFiles", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "soldQuantity", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Product toEntity(ProductRequestDto dto);

    @Mapping(target = "sellerId", expression = "java(entity.getSeller().getId())")
    @Mapping(target = "categoryId", expression = "java(entity.getCategory().getId())")
    ProductResponseDto toResponseDto(Product entity);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "productFiles", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "soldQuantity", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateEntity(ProductRequestDto dto, @MappingTarget Product entity);

}
