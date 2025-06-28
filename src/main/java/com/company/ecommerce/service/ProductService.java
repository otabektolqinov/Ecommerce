package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.ProductRequestDto;
import com.company.ecommerce.dto.response.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProductService {
    HttpApiResponse<ProductResponseDto> createProduct(ProductRequestDto productRequestDto);

    HttpApiResponse<ProductResponseDto> getProductById(Long id);

    HttpApiResponse<Page<ProductResponseDto>> getAllProductByCategoryName(String categoryName, Pageable pageable);

    HttpApiResponse<Page<ProductResponseDto>> getAllProductBySellerId(Long sellerId, Pageable pageable);

    HttpApiResponse<ProductResponseDto> updateProduct(ProductRequestDto productRequestDto, Long id);

    HttpApiResponse<String> deleteProductById(Long id);

}
