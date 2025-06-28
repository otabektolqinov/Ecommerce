package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.ProductRequestDto;
import com.company.ecommerce.dto.response.ProductResponseDto;
import com.company.ecommerce.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<ProductResponseDto>> createProduct(
            @RequestBody ProductRequestDto productRequestDto
    ) {
        HttpApiResponse<ProductResponseDto> response = productService.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<HttpApiResponse<ProductResponseDto>> getProductById(@PathVariable Long id) {
        HttpApiResponse<ProductResponseDto> response = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/categoryName/{categoryName}")
    public ResponseEntity<HttpApiResponse<Page<ProductResponseDto>>> getAllProductByCategoryName
            (@PathVariable String categoryName,
             @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
             @RequestParam(value = "size", required = false, defaultValue = "10") Integer sizNumber
            ) {
        HttpApiResponse<Page<ProductResponseDto>> response
                = productService.getAllProductByCategoryName(
                categoryName,
                PageRequest.of(pageNumber, sizNumber));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/sellerId/{sellerId}")
    public ResponseEntity<HttpApiResponse<Page<ProductResponseDto>>> getAllProductBySellerId(
            @PathVariable Long sellerId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer sizNumber
    ) {
        HttpApiResponse<Page<ProductResponseDto>> response
                = productService.getAllProductBySellerId(sellerId, PageRequest.of(pageNumber, sizNumber));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpApiResponse<ProductResponseDto>> updateProduct(
            @RequestBody ProductRequestDto productRequestDto,
            @PathVariable Long id
    ) {
        HttpApiResponse<ProductResponseDto> response = productService.updateProduct(productRequestDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpApiResponse<String>> deleteProductById(@PathVariable Long id) {
        HttpApiResponse<String> response = productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
