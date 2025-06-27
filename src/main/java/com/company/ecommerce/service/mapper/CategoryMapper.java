package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Category;
import com.company.ecommerce.dto.request.CategoryRequestDto;
import com.company.ecommerce.dto.response.CategoryResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryRequestDto dto);

    CategoryResponseDto toDto(Category category);

    @Mapping(target = "products", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category updateEntity(CategoryRequestDto dto, @MappingTarget Category category);
}
