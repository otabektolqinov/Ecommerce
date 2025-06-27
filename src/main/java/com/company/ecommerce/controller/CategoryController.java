package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.CategoryRequestDto;
import com.company.ecommerce.dto.response.CategoryResponseDto;
import com.company.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<CategoryResponseDto>> createCategory(
            @RequestBody CategoryRequestDto dto
    ) {
        HttpApiResponse<CategoryResponseDto> response = categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpApiResponse<CategoryResponseDto>> getCategoryById(@PathVariable Long id) {
        HttpApiResponse<CategoryResponseDto> response = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<HttpApiResponse<List<CategoryResponseDto>>> getAllCategory() {
        HttpApiResponse<List<CategoryResponseDto>> response = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpApiResponse<CategoryResponseDto>> updateCategoryById(
            @RequestBody CategoryRequestDto dto,
            @PathVariable Long id
    ) {
        HttpApiResponse<CategoryResponseDto> response = categoryService.updateCategoryById(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpApiResponse<String>> deleteCategoryById(
            @PathVariable Long id
    ) {
        HttpApiResponse<String> response = categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}


