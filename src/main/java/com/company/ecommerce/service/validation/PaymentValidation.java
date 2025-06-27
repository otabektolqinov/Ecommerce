package com.company.ecommerce.service.validation;

import com.company.ecommerce.domain.Orders;
import com.company.ecommerce.domain.Users;
import com.company.ecommerce.dto.ErrorDto;
import com.company.ecommerce.repository.OrderRepository;
import com.company.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentValidation {

    private final OrderRepository orderRepository;

    public Optional<ErrorDto> validatePayment(Long orderId){
        Optional<Orders> ordersOptional = orderRepository.findById(orderId);
        if (ordersOptional.isEmpty()){
            return Optional.of(new ErrorDto(
                    "/payment",
                    String.format("Order with %d id is not found", orderId),
                    HttpStatus.NOT_FOUND.value()
            ));
        }
        return Optional.empty();
    }
}
