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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public HttpApiResponse<ProductResponseDto> createProduct(ProductRequestDto requestDto) {
        Category category = productValidation.getCategoryOrThrow(requestDto.getCategoryId());
        Seller seller = productValidation.getSellerOrThrow(requestDto.getSellerId());

        Product product = productMapper.toEntity(requestDto);
        product.setCategory(category);
        product.setSeller(seller);

        category.getProducts().add(product);
        seller.getProducts().add(product);

        float totalRating = 0;
        int productCount = seller.getProducts().size();

        for (Product prod : seller.getProducts()) {
            totalRating += prod.getRating() != null ? (float) prod.getRating() : 0;
        }

        float averageRating = productCount > 0 ? totalRating / productCount : 0;

        seller.setRating(averageRating);

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
    public HttpApiResponse<Page<ProductResponseDto>> getAllProductByCategoryName(
            String categoryName,
            Pageable pageable
    ) {
        Page<Product> allProduct = productRepository.findAllByCategoryNameAndDeletedAtIsNull(categoryName, pageable);
        if (allProduct.isEmpty()) {
            return HttpApiResponse.<Page<ProductResponseDto>>builder()
                    .success(false)
                    .message("Products not found")
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Page<ProductResponseDto> dtoPage = allProduct.map(productMapper::toResponseDto);

        return HttpApiResponse.<Page<ProductResponseDto>>builder()
                .success(true)
                .message("OK")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(dtoPage)
                .build();
    }

    @Override
    public HttpApiResponse<Page<ProductResponseDto>> getAllProductBySellerId(
            Long sellerId,
            Pageable pageable
    ) {
        Seller seller = productValidation.getSellerOrThrow(sellerId);
        Page<Product> productPageList = productRepository.findAllBySellerIdAndDeletedAtIsNull(sellerId, pageable);
        if (productPageList.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("ProductBy Seller ID", sellerId);
        }

        Page<ProductResponseDto> dtoPage = productPageList.map(productMapper::toResponseDto);
        return HttpApiResponse.<Page<ProductResponseDto>>builder()
                .success(true)
                .message("OK")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(dtoPage)
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
