package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.OrderRequestDto;
import com.company.ecommerce.dto.response.OrderResponseDto;
import com.company.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<OrderResponseDto>> createOrder(@RequestBody OrderRequestDto dto){
        HttpApiResponse<OrderResponseDto> response = orderService.createOrder(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<HttpApiResponse<OrderResponseDto>> getOrderById(@RequestParam("orderId") Long id){
        HttpApiResponse<OrderResponseDto> response = orderService.getOrderById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<HttpApiResponse<List<OrderResponseDto>>> getAllOrdersByUsersId(@PathVariable("userId") Long userId){
        HttpApiResponse<List<OrderResponseDto>> response = orderService.getAllOrdersByUsersId(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
