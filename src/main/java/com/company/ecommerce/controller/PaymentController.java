package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<PaymentResponseDto>> createPayment(@RequestBody PaymentRequestDto dto){
        HttpApiResponse<PaymentResponseDto> response = paymentService.createPayment(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
