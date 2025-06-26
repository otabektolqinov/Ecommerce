package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.SellerRequest;
import com.company.ecommerce.dto.response.SellerResponse;
import org.springframework.stereotype.Service;

@Service
public interface SellerService {
    HttpApiResponse<SellerResponse> createSeller(SellerRequest request);

    HttpApiResponse<SellerResponse> getSellerById(Long id);

    HttpApiResponse<SellerResponse> updateSellerById(SellerRequest request, Long id);

    HttpApiResponse<String> deleteSellerById(Long id);

}
