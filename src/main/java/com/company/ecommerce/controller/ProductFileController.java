package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.ProductFileResponseDto;
import com.company.ecommerce.service.ProductFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prod-file")
public class ProductFileController {
    private final ProductFileService productFileService;

    @PostMapping(value = "/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpApiResponse<String>> uploadFileToProduct(
            @RequestPart List<MultipartFile> files,
            @PathVariable Long productId) throws IOException {

        HttpApiResponse<String> response = productFileService.createProductFile(files, productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/get-all/{prodId}")
    public ResponseEntity<HttpApiResponse<List<ProductFileResponseDto>>> getAllProductFilesByProdId(@PathVariable Long prodId) {
        HttpApiResponse<List<ProductFileResponseDto>> response = productFileService.getAllProductFilesByProdId(prodId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{fileId}")
    public ResponseEntity<HttpApiResponse<String>> deleteFileById(@PathVariable Long fileId) {
        HttpApiResponse<String> response = productFileService.deleteFileById(fileId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
