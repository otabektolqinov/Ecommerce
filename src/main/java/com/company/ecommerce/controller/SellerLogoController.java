package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.SellerLogoResponse;
import com.company.ecommerce.service.SellerLogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellerLogo")
public class SellerLogoController {
    private final SellerLogoService sellerLogoService;


    @PostMapping(value = "/{sellerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpApiResponse<String>> uploadFileToSeller(
            @RequestPart List<MultipartFile> files,
            @PathVariable Long sellerId) throws IOException {

        HttpApiResponse<String> response = sellerLogoService.createSellerLogo(files, sellerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/get-all-by/{sellerId}")
    public ResponseEntity<HttpApiResponse<Set<SellerLogoResponse>>> getAllSellerFilesBySellerId(@PathVariable Long sellerId) {
        HttpApiResponse<Set<SellerLogoResponse>> response = sellerLogoService.getAllSellerLogoBySellerId(sellerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{logoId}")
    public ResponseEntity<HttpApiResponse<String>> deleteLogoById(@PathVariable Long logoId) {
        HttpApiResponse<String> response = sellerLogoService.deleteLogoById(logoId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
