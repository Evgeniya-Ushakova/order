package com.evg.order.service;

import com.evg.order.dto.delivery.response.DeliveryBackResponse;
import com.evg.order.dto.delivery.response.DeliveryResponse;

import java.time.LocalDateTime;

public interface DeliveryService {

    DeliveryResponse reserveDelivery(Long orderId,
                                     LocalDateTime timeTo,
                                     LocalDateTime timeFrom,
                                     String address);

    DeliveryBackResponse backDelivery(Long reservedId);

}
