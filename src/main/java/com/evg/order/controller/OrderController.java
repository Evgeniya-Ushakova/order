package com.evg.order.controller;


import com.evg.order.dto.order.request.CreateOrderRequest;
import com.evg.order.dto.order.response.CreateOrderResponse;
import com.evg.order.dto.order.response.GenerateOrderNumberResponse;
import com.evg.order.service.AuthService;
import com.evg.order.service.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final static String IDEMPOTENCY_KEY_HEADER = "Idempotency-Key";

    private final OrderService orderService;
    private final AuthService authService;

    @PostMapping("")
    public CreateOrderResponse create(@RequestHeader(IDEMPOTENCY_KEY_HEADER) String idempotencyKey,
                                      @RequestBody @Valid @NonNull CreateOrderRequest request,
                                      @RequestHeader("x-auth-token") String authToken) {
        authService.checkAuth(authToken, request.getUserId());
        return orderService.create(request, idempotencyKey);
    }

}
