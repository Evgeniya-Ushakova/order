package com.evg.order.service;


import com.evg.order.dto.order.request.CreateOrderRequest;
import com.evg.order.dto.order.response.CreateOrderResponse;

public interface OrderService {

    CreateOrderResponse create(CreateOrderRequest request, String idempotencyKey);

    CreateOrderResponse removeOrder(String idempotencyKey);

    CreateOrderResponse getOrder(String idempotencyKey);

}
