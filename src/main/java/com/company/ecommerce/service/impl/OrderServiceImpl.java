package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.CartItem;
import com.company.ecommerce.domain.OrderItem;
import com.company.ecommerce.domain.Orders;
import com.company.ecommerce.domain.Payment;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.OrderRequestDto;
import com.company.ecommerce.dto.response.OrderResponseDto;
import com.company.ecommerce.enums.DeliveryType;
import com.company.ecommerce.enums.OrderStatus;
import com.company.ecommerce.repository.*;
import com.company.ecommerce.service.OrderService;
import com.company.ecommerce.service.mapper.OrderItemMapper;
import com.company.ecommerce.service.mapper.OrderMapper;
import com.company.ecommerce.service.mapper.PaymentMapper;
import com.company.ecommerce.service.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public HttpApiResponse<OrderResponseDto> createOrder(OrderRequestDto dto) {

        List<CartItem> allByUsersId = cartItemRepository.findAllByUsers_Id(dto.getUserId());

        Orders orders = new Orders();
        orders.setDeliveryType(DeliveryType.fromValue(dto.getDeliveryTypeIndex()));
        orders.setOrderDate(LocalDateTime.now());
        orders.setOrderStatus(OrderStatus.NEW);
        orders.setUsers(userRepository.findByIdAndDeletedAtIsNull(dto.getUserId()).get());
        orders.setDeliveryAddress(addressRepository.findByIdAndDeletedAtIsNull(dto.getDeliveryAddressId()).get());

        Orders saved = orderRepository.save(orders);

        List<OrderItem> orderItems = allByUsersId
                .stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setOrders(saved);
                    double priceAtTime = cartItem.getProduct().getPrice();
                    orderItem.setPriceAtTime(priceAtTime);
                    return orderItem;
                })
                .collect(Collectors.toList());

        double orderPrice = orderItems.stream()
                .mapToDouble(orderItem-> orderItem.getPriceAtTime() * orderItem.getQuantity())
                .sum();

        saved.setOrderPrice(orderPrice);
        orders.setOrderItems(orderItems);

        Orders orders1 = orderRepository.save(saved);

        cartItemRepository.deleteAllByUsers_Id(dto.getUserId());

        return HttpApiResponse.<OrderResponseDto>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .content(orderMapper.toDto(orders1))
                .responseCode(HttpStatus.CREATED.value())
                .message("Successfully created Order")
                .build();
    }

    @Override
    public HttpApiResponse<OrderResponseDto> getOrderById(Long id) {
        Optional<Orders> optional = orderRepository.findById(id);
        if (optional.isEmpty()){
            return ResponseUtils.buildNotFoundResponse("Order", id);
        }

        Optional<Payment> optionalPayment = paymentRepository.findByOrders_Id(id);
        if (optionalPayment.isEmpty()){
            return ResponseUtils.buildNotFoundResponse("Payment", id);
        }

        Orders orders = optional.get();

        OrderResponseDto dto = orderMapper.toDto(orders);
        dto.setOrderItems(orderItemMapper.toDtoList(orders.getOrderItems()));
        dto.setPayment(paymentMapper.toDto(optionalPayment.get()));

        return HttpApiResponse.<OrderResponseDto>builder()
                .content(dto)
                .success(true)
                .responseCode(HttpStatus.OK.value())
                .message("OK")
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public HttpApiResponse<List<OrderResponseDto>> getAllOrdersByUsersId(Long userId) {
        List<Orders> orders = orderRepository.findAllByUsers_IdAndDeletedAtIsNull(userId);
        if (orders.isEmpty())
            return HttpApiResponse.<List<OrderResponseDto>>builder()
                    .message("No orders yet")
                    .status(HttpStatus.NO_CONTENT)
                    .responseCode(HttpStatus.NO_CONTENT.value())
                    .success(true)
                    .build();

        List<OrderResponseDto> dtoList = orderMapper.toDtoList(orders);

        return HttpApiResponse.<List<OrderResponseDto>>builder()
                .success(true)
                .responseCode(HttpStatus.OK.value())
                .content(dtoList)
                .message("OK")
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public HttpApiResponse<OrderResponseDto> updateOrderStatusById(Long orderId, Integer orderStatusIndex) {
        Optional<Orders> optional = orderRepository.findById(orderId);
        if (optional.isEmpty())
            return ResponseUtils.buildNotFoundResponse("Order", orderId);

        Orders orders = optional.get();
        orders.setOrderStatus(OrderStatus.fromValue(orderStatusIndex));

        Orders updated = orderRepository.save(orders);

        return HttpApiResponse.<OrderResponseDto>builder()
                .status(HttpStatus.OK)
                .message("Successfully updated Status")
                .content(orderMapper.toDto(updated))
                .success(true)
                .responseCode(HttpStatus.OK.value())
                .build();
    }
}
