package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.ProductRequestDto;
import com.company.ecommerce.dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProductService {
    HttpApiResponse<ProductResponseDto> createProduct(ProductRequestDto productRequestDto, List<MultipartFile> productFiles);

    HttpApiResponse<ProductResponseDto> getProductById(Long id);

    HttpApiResponse<List<ProductResponseDto>> getAllProductByCategoryName(String categoryName);

    HttpApiResponse<List<ProductResponseDto>> getAllProductBySellerId(Long sellerId);

    HttpApiResponse<ProductResponseDto> updateProduct(ProductRequestDto productRequestDto, Long id);

    HttpApiResponse<String> deleteProductById(Long id);

}
