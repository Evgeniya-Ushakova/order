package com.evg.order.service.impl;

import com.evg.order.dto.order.request.CreateOrderRequest;
import com.evg.order.service.CamundaService;
import com.evg.order.service.PrepareCamundaProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrepareCamundaProcessServiceImpl implements PrepareCamundaProcessService {

    private final CamundaService camundaService;

    @Override
    public void startCreateOrderProcess(CreateOrderRequest request, String idempotencyKey) {



    }

}
