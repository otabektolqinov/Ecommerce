package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.AddressRequestDto;
import com.company.ecommerce.dto.response.AddressResponseDto;
import com.company.ecommerce.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<AddressResponseDto>> createAddress(@RequestBody AddressRequestDto dto){
        HttpApiResponse<AddressResponseDto> response = addressService.createAddress(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<HttpApiResponse<AddressResponseDto>> getAddressById(@RequestParam("addressId") Long id){
        HttpApiResponse<AddressResponseDto> response = addressService.getAddressById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-delivery-points")
    public ResponseEntity<HttpApiResponse<List<AddressResponseDto>>> getDeliveryPoints(){
        HttpApiResponse<List<AddressResponseDto>> response = addressService.getDeliveryPoints();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<HttpApiResponse<AddressResponseDto>> updateAddressById(
            @RequestParam("addressId") Long id,
            @RequestBody AddressRequestDto dto
    ){
        HttpApiResponse<AddressResponseDto> response = addressService.updateAddressById(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpApiResponse<AddressResponseDto>> deleteAddressById(@RequestParam("addressId") Long id){
        HttpApiResponse<AddressResponseDto> response = addressService.deleteAddressById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
