package com.evg.order.camunda;

import com.evg.order.dto.delivery.response.DeliveryResponse;
import com.evg.order.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.evg.order.enums.BpmnVars.*;


@Slf4j(topic = "DELEGATOR")
@Service("orderDeliveryDelegator")
@RequiredArgsConstructor
public class OrderDeliveryDelegator implements JavaDelegate {

    private final DeliveryService deliveryService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long orderId = (Long)execution.getVariable(ORDER_ID.getName());
        LocalDateTime timeTo = (LocalDateTime) execution.getVariable(TIME_TO.getName());
        LocalDateTime timeFrom = (LocalDateTime) execution.getVariable(TIME_FROM.getName());
        String address = (String) execution.getVariable(ADDRESS.getName());

        try {
            DeliveryResponse deliveryResponse = deliveryService.reserveDelivery(orderId, timeTo, timeFrom, address);
            execution.setVariable(DELIVERY_RESERVATION_ID.getName(), deliveryResponse.getReservedId());
        } catch (Exception e) {
            throw new BpmnError("Delivery service failed", e);
        }

    }
}
