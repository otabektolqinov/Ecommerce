package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.ProductFile;
import com.company.ecommerce.dto.response.ProductFileResponseDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductFileMapper {

    ProductFileResponseDto toResponseDto(ProductFile productFile);

    @Named("toProductFile")
    @Mapping(target = "originalName", expression = "java(file.getOriginalFilename())")
    @Mapping(target = "generatedName", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "mimeType", expression = "java(file.getContentType())")
    @Mapping(target = "size", expression = "java(file.getSize())")
    @Mapping(target = "contentUrl", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductFile toEntity(MultipartFile file) throws IOException;

    @IterableMapping(qualifiedByName = "toProductFile")
    List<ProductFile> toEntityList(List<MultipartFile> files);
}
