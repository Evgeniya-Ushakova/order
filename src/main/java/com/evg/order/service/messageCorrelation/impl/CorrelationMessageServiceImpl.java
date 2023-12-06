package com.evg.order.service.messageCorrelation.impl;

import com.evg.order.dto.storehouse.message.CorrelationMessageMessage;
import com.evg.order.enums.BpmnCorrelationMessage;
import com.evg.order.enums.BpmnProcess;
import com.evg.order.enums.BpmnVars;
import com.evg.order.enums.OrderEvent;
import com.evg.order.service.CamundaService;
import com.evg.order.service.messageCorrelation.CorrelationMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/correlate")
@Slf4j(topic = "CorrelationMessageService")
@RequiredArgsConstructor
public class CorrelationMessageServiceImpl implements CorrelationMessageService {

    private final CamundaService camundaService;

    @Override
    @PostMapping("/message")
    public void correlateMessage(@RequestBody CorrelationMessageMessage message) {
        camundaService.createMessageCorrelation(BpmnProcess.CREATE_ORDER,
                message.getOrderKey(),
                getCorrelationMessage(message.getEvent()).name(),
                buildVars(message));
    }

    private Map<String, Object> buildVars(CorrelationMessageMessage message) {
        if (message.getEvent() == OrderEvent.DELIVERY) {
            return Map.of(BpmnVars.DELIVERY_STATUS.getName(), message.getStatus(),
                    BpmnVars.EVENT_SUCCESS.getName(), message.getSuccess());
        }
        return Map.of(BpmnVars.PACKAGE_STATUS.getName(), message.getStatus(),
                BpmnVars.EVENT_SUCCESS.getName(), message.getSuccess());
    }

    private BpmnCorrelationMessage getCorrelationMessage(OrderEvent event) {
        return event == OrderEvent.DELIVERY
                ? BpmnCorrelationMessage.ORDER_DELIVERY
                : BpmnCorrelationMessage.ORDER_PACKAGED;
    }

}
