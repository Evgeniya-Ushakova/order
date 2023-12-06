package com.evg.order.camunda;

import com.evg.order.enums.BpmnVars;
import com.evg.order.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("backToStoreHouseDelegator")
@Slf4j(topic = "DELEGATOR")
@RequiredArgsConstructor
public class BackToStoreHouseDelegator implements JavaDelegate {

    private final DeliveryService deliveryService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long reservationId = (Long)execution.getVariable(BpmnVars.DELIVERY_RESERVATION_ID.getName());
        deliveryService.backDelivery(reservationId);
    }

}
