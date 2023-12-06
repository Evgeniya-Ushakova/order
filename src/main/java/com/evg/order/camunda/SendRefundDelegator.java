package com.evg.order.camunda;

import com.evg.order.entity.Order;
import com.evg.order.repository.OrderRepository;
import com.evg.order.service.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;


@Slf4j(topic = "DELEGATOR")
@Service("sendRefundDelegator")
@RequiredArgsConstructor
public class SendRefundDelegator implements JavaDelegate {

    private final BillingService billingService;
    private final OrderRepository orderRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Order order = orderRepository.findByOrderKeyOrElseThrow(execution.getBusinessKey());
        billingService.updateBillingAccount(order.getUserId(), order.getTotalPrice(), true);
    }
}
