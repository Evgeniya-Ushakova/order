package com.evg.order.service.impl;

import com.evg.order.dto.delivery.request.DeliveryBackRequest;
import com.evg.order.dto.delivery.request.DeliveryRequest;
import com.evg.order.dto.delivery.response.DeliveryBackResponse;
import com.evg.order.dto.delivery.response.DeliveryResponse;
import com.evg.order.service.DeliveryService;
import com.evg.order.service.client.DeliveryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j(topic = "DELIVERY_SERVICE")
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryClient deliveryClient;

    @Override
    public DeliveryResponse reserveDelivery(Long orderId, LocalDateTime timeTo, LocalDateTime timeFrom, String address) {
        DeliveryRequest request = new DeliveryRequest();
        request.setOrderId(orderId);
        request.setTimeTo(timeTo);
        request.setTimeFrom(timeFrom);
        request.setAddress(address);
        return deliveryClient.reserveDelivery(request);
    }

    @Override
    public DeliveryBackResponse backDelivery(Long reservedId) {
        DeliveryBackRequest request = new DeliveryBackRequest();
        request.setReservedId(reservedId);
        return deliveryClient.backToStorehouse(request);
    }

}
