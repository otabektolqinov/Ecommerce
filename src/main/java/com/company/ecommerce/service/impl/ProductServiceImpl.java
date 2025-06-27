package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Category;
import com.company.ecommerce.domain.Product;
import com.company.ecommerce.domain.ProductFile;
import com.company.ecommerce.domain.Seller;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.ProductRequestDto;
import com.company.ecommerce.dto.response.ProductResponseDto;
import com.company.ecommerce.repository.ProductRepository;
import com.company.ecommerce.service.ProductService;
import com.company.ecommerce.service.mapper.ProductMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import com.company.ecommerce.service.validation.ProductValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductValidation productValidation;

    @Override
    public HttpApiResponse<ProductResponseDto> createProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.toEntity(productRequestDto);
        Category category = productValidation.categoryExist(productRequestDto.getCategoryId());
        Seller seller = productValidation.sellerExist(productRequestDto.getSellerId());
        if (category == null && seller == null) {
            return HttpApiResponse.<ProductResponseDto>builder()
                    .success(false)
                    .message("Unable to upload product")
                    .status(HttpStatus.BAD_REQUEST)
                    .responseCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }


        Product saved = productRepository.save(product);
        return HttpApiResponse.<ProductResponseDto>builder()
                .success(true)
                .message("Product saved successfully")
                .responseCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .content(productMapper.toResponseDto(saved))
                .build();
    }

    @Override
    public HttpApiResponse<ProductResponseDto> getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findByIdAndDeletedAtIsNull(id);
        if (optionalProduct.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Product", id);
        }

        return HttpApiResponse.<ProductResponseDto>builder()
                .success(true)
                .message("Product found")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(productMapper.toResponseDto(optionalProduct.get()))
                .build();
    }

    @Override
    public HttpApiResponse<List<ProductResponseDto>> getAllProductByCategoryName(String categoryName) {
        Optional<List<Product>> allProduct = productRepository.findAllByCategoryNameAndDeletedAtIsNull(categoryName);
        if (allProduct.isEmpty()) {
            return HttpApiResponse.<List<ProductResponseDto>>builder()
                    .success(false)
                    .message("Products not found")
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return HttpApiResponse.<List<ProductResponseDto>>builder()
                .success(true)
                .message("OK")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(allProduct.get().stream().map(productMapper::toResponseDto).toList())
                .build();
    }

    @Override
    public HttpApiResponse<List<ProductResponseDto>> getAllProductBySellerId(Long sellerId) {
        Seller seller = productValidation.sellerExist(sellerId);
        Optional<List<Product>> optionalProductList = productRepository.findAllBySellerIdAndDeletedAtIsNull(sellerId);
        if (seller == null) {
            return ResponseUtils.buildNotFoundResponse("Seller", sellerId);
        }
        if (optionalProductList.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("ProductBy Seller ID", sellerId);
        }

        return HttpApiResponse.<List<ProductResponseDto>>builder()
                .success(true)
                .message("OK")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(optionalProductList.get().stream().map(productMapper::toResponseDto).toList())
                .build();
    }

    @Override
    public HttpApiResponse<ProductResponseDto> updateProduct(ProductRequestDto productRequestDto, Long id) {
        Optional<Product> optionalProduct = productRepository.findByIdAndDeletedAtIsNull(id);
        if (optionalProduct.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Product", id);
        }

        Product updatedEntity = productMapper.updateEntity(productRequestDto, optionalProduct.get());
        productRepository.save(updatedEntity);

        return HttpApiResponse.<ProductResponseDto>builder()
                .success(true)
                .message("Product updated successfully")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(productMapper.toResponseDto(updatedEntity))
                .build();
    }

    @Override
    public HttpApiResponse<String> deleteProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findByIdAndDeletedAtIsNull(id);
        if (optionalProduct.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Product", id);
        }
        optionalProduct.get().setDeletedAt(LocalDateTime.now());
        productRepository.save(optionalProduct.get());
        return HttpApiResponse.<String>builder()
                .success(true)
                .message("Product deleted successfully")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build();
    }
}
