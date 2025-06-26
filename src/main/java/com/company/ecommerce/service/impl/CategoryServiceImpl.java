package com.company.ecommerce.service.impl;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.CategoryRequestDto;
import com.company.ecommerce.dto.response.CategoryResponseDto;
import com.company.ecommerce.service.CategoryService;
import com.company.ecommerce.repository.CategoryRepository;
import com.company.ecommerce.service.mapper.CategoryMapper;
import com.company.ecommerce.service.validation.CategoryValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryValidation categoryValidation;

    @Override
    public HttpApiResponse<CategoryResponseDto> createCategory(CategoryRequestDto dto) {
        return null;
    }

    @Override
    public HttpApiResponse<CategoryResponseDto> getCategoryById(Long id) {
        return null;
    }

    @Override
    public HttpApiResponse<List<CategoryResponseDto>> getAllCategory() {
        return null;
    }

    @Override
    public HttpApiResponse<CategoryResponseDto> updateCategoryById(CategoryRequestDto dto, Long id) {
        return null;
    }

    @Override
    public HttpApiResponse<CategoryResponseDto> deleteCategoryById(Long id) {
        return null;
    }
}
