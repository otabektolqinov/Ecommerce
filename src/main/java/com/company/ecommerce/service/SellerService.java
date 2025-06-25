package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.SellerRequest;
import com.company.ecommerce.dto.SellerResponse;
import org.springframework.stereotype.Service;

@Service
public interface SellerService {
    HttpApiResponse<SellerResponse> createSeller(SellerRequest request);
}
