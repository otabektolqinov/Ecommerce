package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.PaymentResponseDto;
import com.company.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<PaymentResponseDto>> createPayment(
            @RequestParam("orderId") Long orderId, Integer paymentTypeIndex
    ){
        HttpApiResponse<PaymentResponseDto> response = paymentService.createPayment(orderId, paymentTypeIndex);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
