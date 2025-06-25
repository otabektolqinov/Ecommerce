package com.company.ecommerce.service.mapper;

import com.company.ecommerce.domain.Seller;
import com.company.ecommerce.dto.SellerRequest;
import com.company.ecommerce.dto.SellerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper {

    SellerResponse mapToSellerResponse(Seller seller);

    Seller mapToSeller(SellerRequest request);
}
