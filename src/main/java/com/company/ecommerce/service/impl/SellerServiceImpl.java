package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Seller;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.SellerRequest;
import com.company.ecommerce.dto.response.SellerResponse;
import com.company.ecommerce.repository.SellerRepository;
import com.company.ecommerce.service.SellerService;
import com.company.ecommerce.service.mapper.SellerMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import com.company.ecommerce.service.validation.SellerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final SellerValidation sellerValidation;

    @Override
    public HttpApiResponse<SellerResponse> createSeller(SellerRequest request) {
        Seller seller = sellerMapper.mapToSeller(request);
        seller.setRegisteredDate(LocalDate.now());
        Seller saved = sellerRepository.save(seller);
        return HttpApiResponse.<SellerResponse>builder()
                .success(true)
                .message("Seller created successfully")
                .status(HttpStatus.CREATED)
                .responseCode(HttpStatus.CREATED.value())
                .content(sellerMapper.mapToSellerResponse(saved))
                .build();
    }


    @Override
    public HttpApiResponse<SellerResponse> getSellerById(Long id) {
        Optional<Seller> seller = sellerRepository.findByIdAndDeletedAtIsNull(id);
        if (seller.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Seller", id);
        }
        return HttpApiResponse.<SellerResponse>builder()
                .success(true)
                .message("Seller found")
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .content(sellerMapper.mapToSellerResponse(seller.get()))
                .build();
    }

    @Override
    public HttpApiResponse<SellerResponse> updateSellerById(SellerRequest request, Long id) {
        Optional<Seller> seller = sellerRepository.findByIdAndDeletedAtIsNull(id);
        if (seller.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Seller", id);
        }
        Seller updatedSeller = sellerMapper.updateSeller(request, seller.get());
        sellerRepository.save(updatedSeller);
        return HttpApiResponse.<SellerResponse>builder()
                .success(true)
                .message("Seller updated successfully")
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .content(sellerMapper.mapToSellerResponse(updatedSeller))
                .build();
    }

    @Override
    public HttpApiResponse<String> deleteSellerById(Long id) {
        Optional<Seller> seller = sellerRepository.findByIdAndDeletedAtIsNull(id);
        if (seller.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Seller", id);
        }
        seller.get().setDeletedAt(LocalDateTime.now());
        sellerRepository.save(seller.get());
        return HttpApiResponse.<String>builder()
                .success(true)
                .message("Seller deleted successfully")
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .build();
    }
}
