package com.evg.order.service.impl;

import com.evg.order.entity.Order;
import com.evg.order.enums.OrderStatus;
import com.evg.order.repository.OrderRepository;
import com.evg.order.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "ORDER_STATUS_SERVICE")
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderRepository orderRepository;

    @Override
    public Order changeStatus(OrderStatus status, String orderKey) {
        Order order = orderRepository.findByOrderKeyOrElseThrow(orderKey);
        order.setStatus(status);
        return orderRepository.save(order);
    }

}
