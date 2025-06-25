package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.SellerRequest;
import com.company.ecommerce.dto.SellerResponse;
import com.company.ecommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
