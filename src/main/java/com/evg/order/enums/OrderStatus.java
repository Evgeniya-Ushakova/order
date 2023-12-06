package com.evg.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    NEW,
    PACKAGING,
    DELIVERING,
    DONE,
    CANCELLED,
    REJECTED;

}
