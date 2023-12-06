package com.evg.order.service.messageCorrelation.handlers.impl;

import com.evg.order.dto.storehouse.message.CorrelationMessageMessage;
import com.evg.order.service.CamundaService;
import com.evg.order.service.messageCorrelation.handlers.CorrelationMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@Slf4j(topic = "DELIVERY_CORRELATION_MESSAGE_HANDLER")
@RequiredArgsConstructor
public class DeliveryCorrelationMessageHandler implements CorrelationMessageHandler {

    private final CamundaService camundaService;

    @Override
    public void correlateMessage(CorrelationMessageMessage message) {

    }

}
