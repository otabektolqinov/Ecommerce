package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Product;
import com.company.ecommerce.domain.ProductFile;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.ProductFileResponseDto;
import com.company.ecommerce.repository.ProductFileRepository;
import com.company.ecommerce.repository.ProductRepository;
import com.company.ecommerce.service.ProductFileService;
import com.company.ecommerce.service.S3Service;
import com.company.ecommerce.service.mapper.ProductFileMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import com.company.ecommerce.service.validation.ProductFileValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductFileServiceImpl implements ProductFileService {

    private final ProductFileRepository productFileRepository;
    private final ProductFileMapper productFileMapper;
    private final ProductFileValidation productFileValidation;
    private final ProductRepository productRepository;
    private final S3Service s3Service;


    @Override
    public HttpApiResponse<String> createProductFile(List<MultipartFile> files, Long productId) throws IOException {
        Optional<Product> optionalProduct = productRepository.findByIdAndDeletedAtIsNull(productId);
        if (optionalProduct.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Product", productId);
        }

        List<ProductFile> entityList = new ArrayList<>();

        for (MultipartFile file : files) {
            String generatedName = file.getOriginalFilename() + UUID.randomUUID();
            String url = s3Service.uploadFile(file.getBytes(), file.getContentType(), generatedName);

            ProductFile pFile = ProductFile.builder()
                    .contentUrl(url)
                    .originalName(file.getOriginalFilename())
                    .generatedName(generatedName)
                    .mimeType(file.getContentType())
                    .size(file.getSize())
                    .product(optionalProduct.get())
                    .build();

            entityList.add(pFile);

        }
        productFileRepository.saveAll(entityList);
        return HttpApiResponse.<String>builder()
                .success(true)
                .message("Product file created")
                .responseCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public HttpApiResponse<List<ProductFileResponseDto>> getAllProductFilesByProdId(Long prodId) {
        Optional<Product> optionalProduct = productRepository.findByIdAndDeletedAtIsNull(prodId);
        if (optionalProduct.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Product", prodId);
        }

        Product product = optionalProduct.get();
        Set<ProductFile> productFiles = product.getProductFiles();

        List<ProductFileResponseDto> dtos = productFiles.stream()
                .map(productFileMapper::toResponseDto)
                .collect(Collectors.toList());

        return HttpApiResponse.<List<ProductFileResponseDto>>builder()
                .success(true)
                .message("Product files found")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(dtos)
                .build();
    }


    @Override
    public HttpApiResponse<String> deleteFileById(Long fileId) {
        Optional<ProductFile> fileOptional = productFileRepository.findProductFileByIdAndDeletedAtIsNull(fileId);
        if (fileOptional.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("ProductFile", fileId);
        }
        fileOptional.get().setDeletedAt(LocalDateTime.now());
        productFileRepository.save(fileOptional.get());
        return HttpApiResponse.<String>builder()
                .success(true)
                .message("Product file deleted")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build();
    }
}
