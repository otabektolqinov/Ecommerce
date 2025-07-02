package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.SellerRequestDto;
import com.company.ecommerce.dto.response.SellerResponseDto;
import com.company.ecommerce.service.SellerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class SellerController {
    private final SellerService sellerService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<HttpApiResponse<SellerResponseDto>> createSeller
            (
                    @RequestBody @Valid SellerRequestDto requestDto
            ) {
        HttpApiResponse<SellerResponseDto> response = sellerService.createSeller(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpApiResponse<SellerResponseDto>> getSellerById(@PathVariable Long id) {
        HttpApiResponse<SellerResponseDto> response = sellerService.getSellerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpApiResponse<SellerResponseDto>> updateSellerById(@RequestBody SellerRequestDto request, @PathVariable Long id) {
        HttpApiResponse<SellerResponseDto> response = sellerService.updateSellerById(request, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpApiResponse<String>> deleteSellerById(@PathVariable Long id) {
        HttpApiResponse<String> response = sellerService.deleteSellerById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
