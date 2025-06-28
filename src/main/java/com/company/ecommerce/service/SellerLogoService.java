package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.SellerLogoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public interface SellerLogoService {
    HttpApiResponse<String> createSellerLogo(List<MultipartFile> files, Long sellerId) throws IOException;

    HttpApiResponse<Set<SellerLogoResponse>> getAllSellerLogoBySellerId(Long sellerId);

    HttpApiResponse<String> deleteLogoById(Long logoId);
}
