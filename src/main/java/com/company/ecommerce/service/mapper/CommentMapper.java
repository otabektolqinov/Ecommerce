package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Category;
import com.company.ecommerce.domain.Comment;
import com.company.ecommerce.dto.request.CommentRequestDto;
import com.company.ecommerce.dto.response.CategoryResponseDto;
import com.company.ecommerce.dto.response.CommentResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "commentFileList", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "users", ignore = true)
    Comment toEntity(CommentRequestDto dto);

    CommentResponseDto toDto(Comment category);

    @Mapping(target = "commentFileList", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "users", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment updateEntity(CommentRequestDto dto, @MappingTarget Comment comment);
}
