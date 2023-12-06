package com.evg.order.camunda;

import com.evg.order.dto.storehouse.ReservationDto;
import com.evg.order.dto.storehouse.response.ReservationResponse;
import com.evg.order.enums.BpmnVars;
import com.evg.order.service.StorehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j(topic = "DELEGATOR")
@Service("orderPackageDelegator")
@RequiredArgsConstructor
public class OrderPackageDelegator implements JavaDelegate {

    private final StorehouseService storehouseService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try {
            ReservationResponse reservationResponse = storehouseService.reserveProduct(execution.getBusinessKey());

            List<Long> reservationIds = reservationResponse.getReservationProducts().stream()
                    .map(ReservationDto::getReservationId)
                    .toList();

            execution.setVariable(BpmnVars.STOREHOUSE_RESERVATION_IDS.getName(), reservationIds);
        } catch (Exception e) {
            throw new BpmnError("Storehouse request failed:", e);
        }

    }
}
