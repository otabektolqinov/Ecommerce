package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Seller;
import com.company.ecommerce.domain.SellerLogo;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.SellerLogoResponse;
import com.company.ecommerce.repository.SellerLogoRepository;
import com.company.ecommerce.repository.SellerRepository;
import com.company.ecommerce.service.S3Service;
import com.company.ecommerce.service.SellerLogoService;
import com.company.ecommerce.service.mapper.SellerLogoMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class SellerLogoServiceImpl implements SellerLogoService {

    private final SellerLogoRepository sellerLogoRepository;
    private final SellerRepository sellerRepository;
    private final SellerLogoMapper sellerLogoMapper;
    private final S3Service s3Service;


    @Override
    public HttpApiResponse<String> createSellerLogo(List<MultipartFile> files, Long sellerId) throws IOException {
        Optional<Seller> seller = sellerRepository.findByIdAndDeletedAtIsNull(sellerId);
        if (seller.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Seller", sellerId);
        }
        List<SellerLogo> sellerLogos = new ArrayList<>();
        for (MultipartFile file : files) {
            String generatedName = file.getOriginalFilename() + UUID.randomUUID();
            String url = s3Service.uploadFile(file.getBytes(), file.getContentType(), generatedName);

            SellerLogo sellerLogo = SellerLogo.builder()
                    .seller(seller.get())
                    .size(file.getSize())
                    .contentUrl(url)
                    .originalName(file.getOriginalFilename())
                    .generatedName(generatedName)
                    .mimeType(file.getContentType())
                    .build();
            sellerLogos.add(sellerLogo);
        }

        sellerLogoRepository.saveAll(sellerLogos);

        return HttpApiResponse.<String>builder()
                .success(true)
                .message("SellerLogo created")
                .responseCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .content("SellerLogo created")
                .build();
    }

    @Override
    public HttpApiResponse<Set<SellerLogoResponse>> getAllSellerLogoBySellerId(Long sellerId) {
        Optional<Seller> seller = sellerRepository.findByIdAndDeletedAtIsNull(sellerId);
        if (seller.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("Seller", sellerId);
        }

        Set<SellerLogoResponse> responseList
                = sellerLogoMapper.mapToResponse(sellerLogoRepository
                .findAllBySellerIdAndDeletedAtIsNull(sellerId));

        return HttpApiResponse.<Set<SellerLogoResponse>>builder()
                .success(true)
                .message("SellerLogos found")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content(responseList)
                .build();
    }

    @Override
    public HttpApiResponse<String> deleteLogoById(Long logoId) {
        Optional<SellerLogo> sellerLogo = sellerLogoRepository.findByIdAndDeletedAtIsNull(logoId);
        if (sellerLogo.isEmpty()) {
            return ResponseUtils.buildNotFoundResponse("SellerLogo", logoId);
        }

        sellerLogo.get().setDeletedAt(LocalDateTime.now());
        sellerLogoRepository.save(sellerLogo.get());

        return HttpApiResponse.<String>builder()
                .success(true)
                .message("SellerLogo deleted")
                .responseCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .content("SellerLogo deleted")
                .build();
    }
}
