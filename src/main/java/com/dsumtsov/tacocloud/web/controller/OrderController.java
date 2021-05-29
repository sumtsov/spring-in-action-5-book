package com.dsumtsov.tacocloud.web.controller;

import com.dsumtsov.tacocloud.domain.Order;
import com.dsumtsov.tacocloud.domain.User;
import com.dsumtsov.tacocloud.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user,
                                Model model) {

        model.addAttribute("orders", orderService.findByUser(user));

        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        orderService.save(order);
        sessionStatus.setComplete(); // resets the session to remove processed order from it

        return "redirect:/";
    }
}
