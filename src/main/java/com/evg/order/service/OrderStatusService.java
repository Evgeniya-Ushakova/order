package com.evg.order.service;

import com.evg.order.entity.Order;
import com.evg.order.enums.OrderStatus;

public interface OrderStatusService {

    Order changeStatus(OrderStatus status, String orderKey);

}
