package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Seller;
import com.company.ecommerce.dto.request.SellerRequestDto;
import com.company.ecommerce.dto.response.SellerResponseDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    SellerLogoMapper sellerLogoMapper = Mappers.getMapper(SellerLogoMapper.class);
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);


    @Mapping(target = "products", expression = ("java(productMapper.mapToResponseDtoSet(seller.getProducts()))"))
    @Mapping(target = "sellerLogo", expression = ("java(sellerLogoMapper.mapToResponse(seller.getSellerLogo()))"))
    SellerResponseDto mapToSellerResponse(Seller seller);


    @Mapping(target = "commentCount", ignore = true)
    @Mapping(target = "orderCount", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "registeredDate", ignore = true)
    @Mapping(target = "sellerLogo", ignore = true)
    @Mapping(target = "products", ignore = true)
    Seller mapToSeller(SellerRequestDto request);


    @Mapping(target = "commentCount", ignore = true)
    @Mapping(target = "orderCount", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "registeredDate", ignore = true)
    @Mapping(target = "sellerLogo", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Seller updateSeller(SellerRequestDto request, @MappingTarget Seller seller);
}
