package com.company.ecommerce.service.impl;

import com.company.ecommerce.domain.CartItem;
import com.company.ecommerce.domain.OrderItem;
import com.company.ecommerce.domain.Orders;
import com.company.ecommerce.domain.Payment;
import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.OrderRequestDto;
import com.company.ecommerce.dto.response.OrderItemResponseDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public HttpApiResponse<OrderResponseDto> createOrder(OrderRequestDto dto) {
        // todo: 1 -> UserId boyicha CartItem larni topish kerak
        List<CartItem> allByUsersId = cartItemRepository.findAllByUsers_Id(dto.getUserId());

        // todo: 2 -> CartItem larni OrderItem ga o'tkazish kerak

        List<OrderItem> orderItems = allByUsersId
                .stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());

                    System.out.println(cartItem.getProduct().getPrice());
                    System.out.println(cartItem.getQuantity());
                    System.out.println(cartItem.getProduct().getPrice() * cartItem.getQuantity());
                    double priceAtTime = cartItem.getProduct().getPrice() * cartItem.getQuantity();
                    System.out.println(priceAtTime);
                    orderItem.setPriceAtTime(priceAtTime);

                    return orderItem;
                })
                .toList();

        // todo: 3 -> OrderItem larni Orderga set qilish kerak

        Orders orders = new Orders();
        orders.setDeliveryType(DeliveryType.fromValue(dto.getDeliveryTypeIndex()));
        orders.setOrderDate(LocalDateTime.now());
        orders.setOrderStatus(OrderStatus.NEW);
        orders.setOrderItems(orderItems);
        double orderPrice = orderItems.stream()
                .mapToDouble(OrderItem::getPriceAtTime)
                .sum();
        orders.setOrderPrice(orderPrice);
        orders.setUsers(userRepository.findByIdAndDeletedAtIsNull(dto.getUserId()).get());
        orders.setDeliveryAddress(addressRepository.findByIdAndDeletedAtIsNull(dto.getDeliveryAddressId()).get());

        Orders saved = orderRepository.save(orders);

        cartItemRepository.deleteAllByUsers_Id(dto.getUserId());

        return HttpApiResponse.<OrderResponseDto>builder()
                .success(true)
                .status(HttpStatus.CREATED)
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
        List<OrderItemResponseDto> list = orders.getOrderItems().stream().map(orderItemMapper::toDto).toList();
        System.out.println(list);
        dto.setOrderItems(list);
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
        return null;
    }
}
