package com.evg.order.service;

import com.evg.order.dto.order.request.CreateOrderRequest;

public interface PrepareCamundaProcessService {

    void startCreateOrderProcess(CreateOrderRequest request, String idempotencyKey);

}
