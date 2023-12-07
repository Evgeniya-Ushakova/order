package com.evg.order.controller;


import com.evg.order.dto.order.request.CreateOrderRequest;
import com.evg.order.dto.order.response.CreateOrderResponse;
import com.evg.order.enums.BpmnProcess;
import com.evg.order.service.AuthService;
import com.evg.order.service.BillingService;
import com.evg.order.service.CamundaService;
import com.evg.order.service.OrderService;
import com.evg.order.utils.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.evg.order.enums.BpmnVars.*;
import static com.evg.order.enums.BpmnVars.REQUEST_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final static String IDEMPOTENCY_KEY_HEADER = "Idempotency-Key";

    private final BillingService billingService;
    private final OrderService orderService;
    private final CamundaService camundaService;
    private final AuthService authService;


    @PostMapping("")
    public CreateOrderResponse create(@RequestHeader(IDEMPOTENCY_KEY_HEADER) String idempotencyKey,
                                      @RequestBody @Valid @NonNull CreateOrderRequest request,
                                      @RequestHeader(value = "x-auth-token", required = false) String authToken) {
        authService.checkAuth(authToken, request.getUserId());
        try {
            CreateOrderResponse createOrderResponse = orderService.create(request, idempotencyKey);
            billingService.updateBillingAccount(request.getUserId(), request.getTotalPrice(), false);
            camundaService.startProcess(BpmnProcess.CREATE_ORDER,
                    idempotencyKey,
                    Map.of(ORDER_ID.getName(), createOrderResponse.getOrderId(),
                            TIME_TO.getName(), request.getTimeTo(),
                            TIME_FROM.getName(), request.getTimeFrom(),
                            ADDRESS.getName(), request.getAddress(),
                            REQUEST_ID.getName(), Utils.getMdcRequestId()));

            return createOrderResponse;
        } catch (Exception e) {
            return orderService.removeOrder(idempotencyKey);
        }
    }

    @GetMapping("/{orderKey}")
    public CreateOrderResponse getOrder(@PathVariable String orderKey) {
        return orderService.getOrder(orderKey);
    }

}
