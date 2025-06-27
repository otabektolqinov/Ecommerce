package com.company.ecommerce.service;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.OrderRequestDto;
import com.company.ecommerce.dto.response.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    HttpApiResponse<OrderResponseDto> createOrder(OrderRequestDto dto);

    HttpApiResponse<OrderResponseDto> getOrderById(Long id);

    HttpApiResponse<List<OrderResponseDto>> getAllOrdersByUsersId(Long userId);

    HttpApiResponse<OrderResponseDto> updateOrderStatusById(Long orderId, Integer orderStatusIndex);
}
