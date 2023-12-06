package com.evg.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BpmnVars {

    ORDER_ID("orderId"),
    TIME_TO("timeTo"),
    TIME_FROM("timeFrom"),
    ADDRESS("address"),
    REQUEST_ID("requestId"),
    STOREHOUSE_RESERVATION_IDS("storehouseReservationIds"),
    DELIVERY_RESERVATION_ID("deliveryReservationId"),
    PACKAGE_STATUS("packageStatus"),
    DELIVERY_STATUS("deliveryStatus"),
    EVENT_SUCCESS("eventIsSuccess");

    String name;

    BpmnVars(String name) {
        this.name = name;
    }

}
