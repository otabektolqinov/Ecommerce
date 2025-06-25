package com.company.ecommerce.service.impl;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.SellerRequest;
import com.company.ecommerce.dto.SellerResponse;
import com.company.ecommerce.repository.SellerRepository;
import com.company.ecommerce.service.SellerService;
import com.company.ecommerce.service.mapper.SellerMapper;
import com.company.ecommerce.service.validation.SellerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final SellerValidation sellerValidation;

    @Override
    public HttpApiResponse<SellerResponse> createSeller(SellerRequest request) {
        return null;
    }
}
