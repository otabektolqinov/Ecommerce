package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.AddressRequestDto;
import com.company.ecommerce.dto.response.AddressResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    HttpApiResponse<AddressResponseDto> createAddress(AddressRequestDto dto);
    HttpApiResponse<AddressResponseDto> getAddressById(Long id);
    HttpApiResponse<List<AddressResponseDto>> getDeliveryPoints();
    HttpApiResponse<AddressResponseDto> updateAddressById(Long id, AddressRequestDto dto);
    HttpApiResponse<AddressResponseDto> deleteAddressById(Long id);

}
