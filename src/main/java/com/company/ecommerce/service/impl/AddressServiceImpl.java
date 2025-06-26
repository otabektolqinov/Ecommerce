package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Address;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.AddressRequestDto;
import com.company.ecommerce.dto.response.AddressResponseDto;
import com.company.ecommerce.enums.AddressType;
import com.company.ecommerce.repository.AddressRepository;
import com.company.ecommerce.service.AddressService;
import com.company.ecommerce.service.mapper.AddressMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public HttpApiResponse<AddressResponseDto> createAddress(AddressRequestDto dto) {
        Address address = addressMapper.toEntity(dto);
        Address saved = addressRepository.save(address);

        return HttpApiResponse.<AddressResponseDto>builder()
                .success(true)
                .content(addressMapper.toDto(saved))
                .responseCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .message("Successfully created Address")
                .build();
    }

    @Override
    public HttpApiResponse<AddressResponseDto> getAddressById(Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isEmpty()){
            return ResponseUtils.buildNotFoundResponse("Address", id);
        }

        return HttpApiResponse.<AddressResponseDto>builder()
                .message("OK")
                .success(true)
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .content(addressMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public HttpApiResponse<List<AddressResponseDto>> getDeliveryPoints() {
        List<Address> allByAddressType = addressRepository.findAllByAddressType(AddressType.MARKET);
        if (allByAddressType.isEmpty()){
            return HttpApiResponse.<List<AddressResponseDto>>builder()
                    .message("Delivery points list is empty")
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
        }

        return HttpApiResponse.<List<AddressResponseDto>>builder()
                .success(true)
                .status(HttpStatus.OK)
                .responseCode(HttpStatus.OK.value())
                .message("OK")
                .content(allByAddressType.stream().map(addressMapper :: toDto).toList())
                .build();
    }

    @Override
    public HttpApiResponse<AddressResponseDto> updateAddressById(Long id, AddressRequestDto dto) {
        return null;
    }

    @Override
    public HttpApiResponse<AddressResponseDto> deleteAddressById(Long id) {
        return null;
    }
}
