package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.CommentFile;
import com.company.ecommerce.dto.response.CommentFileResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentFileMapper {

    CommentFileResponseDto toResponseDto(CommentFile commentFile);

}
