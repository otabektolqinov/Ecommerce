package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.ProductFileResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductFileService {
    HttpApiResponse<String> createProductFile(List<MultipartFile> files, Long productId) throws IOException;

    HttpApiResponse<List<ProductFileResponseDto>> getAllProductFilesByProdId(Long prodId);

    HttpApiResponse<String> deleteFileById(Long fileId);
}
