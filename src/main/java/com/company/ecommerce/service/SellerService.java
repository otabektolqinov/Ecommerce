package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.SellerRequestDto;
import com.company.ecommerce.dto.response.SellerResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface SellerService {
    HttpApiResponse<SellerResponseDto> createSeller(SellerRequestDto request);

    HttpApiResponse<SellerResponseDto> getSellerById(Long id);

    HttpApiResponse<SellerResponseDto> updateSellerById(SellerRequestDto request, Long id);

    HttpApiResponse<String> deleteSellerById(Long id);

}
