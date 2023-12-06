package com.evg.order.camunda;

import com.evg.order.enums.BpmnVars;
import com.evg.order.service.StorehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j(topic = "DELEGATOR")
@Service("disbandmentDelegator")
@RequiredArgsConstructor
public class DisbandmentDelegator implements JavaDelegate {

    private final StorehouseService storehouseService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Long> reservationIds = (List)execution.getVariable(BpmnVars.STOREHOUSE_RESERVATION_IDS.getName());
        storehouseService.cancelReservation(reservationIds);
    }
}
