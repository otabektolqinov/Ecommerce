package com.company.ecommerce.mvc;

import com.company.ecommerce.dto.response.OrderResponseDto;
import com.company.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class OrderMvcController {

    private final OrderService orderService;

    @GetMapping("/get-all")
    public String getAllOrders(Model model){
        List<OrderResponseDto> orders = orderService.getAll().getContent();
        model.addAttribute("orders", orders);
        return "orders/orders :: content";
    }

}
