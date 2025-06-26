package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Seller;
import com.company.ecommerce.dto.request.SellerRequest;
import com.company.ecommerce.dto.response.SellerResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SellerMapper {


    @Mapping(target = "products", ignore = true)
    SellerResponse mapToSellerResponse(Seller seller);

    @Mapping(target = "orderCount", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "commentCount", ignore = true)
    @Mapping(target = "registeredDate", ignore = true)
    @Mapping(target = "sellerLogo", ignore = true)
    @Mapping(target = "products", ignore = true)
    Seller mapToSeller(SellerRequest request);

    @Mapping(target = "orderCount", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "commentCount", ignore = true)
    @Mapping(target = "registeredDate", ignore = true)
    @Mapping(target = "sellerLogo", ignore = true)
    @Mapping(target = "products", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Seller updateSeller(SellerRequest request, @MappingTarget Seller seller);
}
