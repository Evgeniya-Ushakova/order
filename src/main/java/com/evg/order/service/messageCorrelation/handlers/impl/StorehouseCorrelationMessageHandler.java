package com.evg.order.service.messageCorrelation.handlers.impl;

import com.evg.order.dto.storehouse.message.CorrelationMessageMessage;
import com.evg.order.enums.BpmnCorrelationMessage;
import com.evg.order.enums.BpmnProcess;
import com.evg.order.enums.BpmnVars;
import com.evg.order.service.CamundaService;
import com.evg.order.service.messageCorrelation.handlers.CorrelationMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Service
@RestController
@Slf4j(topic = "STOREHOUSE_CORRELATION_MESSAGE_HANDLER")
@RequiredArgsConstructor
public class StorehouseCorrelationMessageHandler implements CorrelationMessageHandler {

    private final CamundaService camundaService;

    @Override
    @PostMapping("/storehouse")
    public void correlateMessage(@RequestBody CorrelationMessageMessage message) {
        camundaService.createMessageCorrelation(BpmnProcess.CREATE_ORDER,
                message.getOrderKey(),
                BpmnCorrelationMessage.ORDER_PACKAGED.name(),
                Map.of(BpmnVars.PACKAGE_STATUS.getName(), message.getStatus()));
    }

}
