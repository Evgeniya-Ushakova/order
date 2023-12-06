package com.evg.order.camunda;

import com.evg.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("createOrder")
@Slf4j(topic = "DELEGATOR")
@RequiredArgsConstructor
public class CreateOrderDelegator implements JavaDelegate {

    private final OrderService orderService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {



    }

}
