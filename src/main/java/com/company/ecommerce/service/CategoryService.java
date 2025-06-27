package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.CategoryRequestDto;
import com.company.ecommerce.dto.response.CategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    HttpApiResponse<CategoryResponseDto> createCategory(CategoryRequestDto dto);

    HttpApiResponse<CategoryResponseDto> getCategoryById(Long id);

    HttpApiResponse<List<CategoryResponseDto>> getAllCategory();

    HttpApiResponse<CategoryResponseDto> updateCategoryById(CategoryRequestDto dto, Long id);

    HttpApiResponse<String> deleteCategoryById(Long id);
}
