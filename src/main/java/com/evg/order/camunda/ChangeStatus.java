package com.evg.order.camunda;

import com.evg.order.entity.Order;
import com.evg.order.enums.OrderStatus;
import com.evg.order.repository.OrderRepository;
import com.evg.order.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Setter
@Service("changeStatus")
@Slf4j(topic = "DELEGATOR")
@RequiredArgsConstructor
public class ChangeStatus implements JavaDelegate {

    private final OrderStatusService orderStatusService;

    private Expression status;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderStatus = (String)status.getValue(execution);
        orderStatusService.changeStatus(OrderStatus.valueOf(orderStatus), execution.getBusinessKey());
    }
}
