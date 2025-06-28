package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.SellerLogo;
import com.company.ecommerce.dto.response.SellerLogoResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SellerLogoMapper {
    @Named(value = "toDto")
    SellerLogoResponse toResponse(SellerLogo sellerLogo);

    @IterableMapping(qualifiedByName = "toDto")
    Set<SellerLogoResponse> mapToResponse(Set<SellerLogo> sellerLogos);
}
