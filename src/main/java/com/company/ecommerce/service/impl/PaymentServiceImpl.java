package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.Payment;
import com.company.ecommerce.dto.ErrorDto;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.response.PaymentResponseDto;
import com.company.ecommerce.enums.PaymentStatus;
import com.company.ecommerce.enums.PaymentType;
import com.company.ecommerce.repository.OrderRepository;
import com.company.ecommerce.repository.PaymentRepository;
import com.company.ecommerce.service.PaymentService;
import com.company.ecommerce.service.mapper.PaymentMapper;
import com.company.ecommerce.service.validation.PaymentValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentValidation paymentValidation;
    private final PaymentMapper paymentMapper;

    @Transactional
    @Override
    public HttpApiResponse<PaymentResponseDto> createPayment(Long orderId, Integer paymentTypeIndex) {
        Optional<ErrorDto> errorDto = paymentValidation.validatePayment(orderId);
        if (errorDto.isPresent()){
            return HttpApiResponse.<PaymentResponseDto>builder()
                    .responseCode(errorDto.get().getErrorCode())
                    .message(errorDto.get().getErrorMessage())
                    .errors(errorDto.get())
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Payment payment = new Payment();
        payment.setPaymentType(PaymentType.fromValue(paymentTypeIndex));
        payment.setOrders(orderRepository.findById(orderId).get());
        if (paymentTypeIndex.equals(1))
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
        else
            payment.setPaymentStatus(PaymentStatus.PENDING);

        payment.setTransactionId(UUID.randomUUID().toString());
        Payment saved = paymentRepository.save(payment);

        return HttpApiResponse.<PaymentResponseDto>builder()
                .status(HttpStatus.CREATED)
                .message("Successfully created Payment")
                .responseCode(HttpStatus.CREATED.value())
                .content(paymentMapper.toDto(saved))
                .success(true)
                .build();
    }
}
