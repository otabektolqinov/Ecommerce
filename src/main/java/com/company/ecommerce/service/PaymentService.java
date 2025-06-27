package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.PaymentResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    HttpApiResponse<PaymentResponseDto> createPayment(Long orderId, Integer paymentTypeIndex);

}
