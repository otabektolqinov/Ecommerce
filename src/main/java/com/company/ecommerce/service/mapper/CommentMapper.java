package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Comment;
import com.company.ecommerce.dto.request.CommentRequestDto;
import com.company.ecommerce.dto.response.CommentResponseDto;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "commentFileList", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "users", ignore = true)
    Comment toEntity(CommentRequestDto dto);

    @Named(value = "toDto")
    @Mapping(target = "productId", expression = ("java(comment.getProduct().getId())"))
    @Mapping(target = "userId", expression = ("java(comment.getUsers().getId())"))
    CommentResponseDto toDto(Comment comment);

    @Mapping(target = "commentFileList", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "users", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment updateEntity(CommentRequestDto dto, @MappingTarget Comment comment);

    @IterableMapping(qualifiedByName = "toDto")
    Set<CommentResponseDto> toDtoSet(Set<Comment> comments);

}
