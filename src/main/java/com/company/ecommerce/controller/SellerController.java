package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.SellerRequest;
import com.company.ecommerce.dto.response.SellerResponse;
import com.company.ecommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class SellerController {
    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<SellerResponse>> createSeller(@RequestBody SellerRequest request) {
        HttpApiResponse<SellerResponse> response = sellerService.createSeller(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpApiResponse<SellerResponse>> getSellerById(@PathVariable Long id) {
        HttpApiResponse<SellerResponse> response = sellerService.getSellerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpApiResponse<SellerResponse>> updateSellerById(@RequestBody SellerRequest request, @PathVariable Long id) {
        HttpApiResponse<SellerResponse> response = sellerService.updateSellerById(request, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpApiResponse<String>> deleteSellerById(@PathVariable Long id) {
        HttpApiResponse<String> response = sellerService.deleteSellerById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
