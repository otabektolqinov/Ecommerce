package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Category;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.CategoryRequestDto;
import com.company.ecommerce.dto.response.CategoryResponseDto;
import com.company.ecommerce.service.CategoryService;
import com.company.ecommerce.repository.CategoryRepository;
import com.company.ecommerce.service.mapper.CategoryMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import com.company.ecommerce.service.validation.CategoryValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryValidation categoryValidation;

    @Override
    public HttpApiResponse<CategoryResponseDto> createCategory(CategoryRequestDto dto) {
        boolean validateCategory = categoryValidation.validateCategory(dto.getName());
        if (!validateCategory) {
            return HttpApiResponse.<CategoryResponseDto>builder()
                    .success(false)
                    .message("Invalid category name")
                    .status(HttpStatus.BAD_REQUEST)
                    .responseCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }

        Category entity = categoryMapper.toEntity(dto);
        categoryRepository.save(entity);

        return HttpApiResponse.<CategoryResponseDto>builder()
                .success(true)
                .message("Category created successfully")
                .responseCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .content(categoryMapper.toDto(entity))
                .build();
    }

    @Override
    public HttpApiResponse<CategoryResponseDto> getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndDeletedAtIsNull(id);
        if (optionalCategory.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Category", id);
        }
        return HttpApiResponse.<CategoryResponseDto>builder()
                .success(true)
                .message("OK")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(categoryMapper.toDto(optionalCategory.get()))
                .build();
    }

    @Override
    public HttpApiResponse<List<CategoryResponseDto>> getAllCategory() {
        Optional<List<Category>> categoryList = categoryRepository.findAllByDeletedAtIsNull();
        if (categoryList.isEmpty()) {
            return HttpApiResponse.<List<CategoryResponseDto>>builder()
                    .success(false)
                    .message("Category list is empty")
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return HttpApiResponse.<List<CategoryResponseDto>>builder()
                .success(true)
                .message("OK")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(categoryList.get().stream().map(categoryMapper::toDto).toList())
                .build();
    }

    @Override
    public HttpApiResponse<CategoryResponseDto> updateCategoryById(CategoryRequestDto dto, Long id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndDeletedAtIsNull(id);
        if (optionalCategory.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Category", id);
        }
        Category updateEntity = categoryMapper.updateEntity(dto, optionalCategory.get());
        categoryRepository.save(updateEntity);

        return HttpApiResponse.<CategoryResponseDto>builder()
                .success(true)
                .message("Category updated successfully")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(categoryMapper.toDto(updateEntity))
                .build();
    }

    @Override
    public HttpApiResponse<String> deleteCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndDeletedAtIsNull(id);
        if (optionalCategory.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Category", id);
        }

        optionalCategory.get().setDeletedAt(LocalDateTime.now());
        categoryRepository.save(optionalCategory.get());

        return HttpApiResponse.<String>builder()
                .success(true)
                .message("Category deleted successfully")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build();
    }
}
