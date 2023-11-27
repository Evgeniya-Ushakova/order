package com.evg.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BpmnVars {

    ORDER_ID("orderId");

    String name;

    BpmnVars(String name) {
        this.name = name;
    }

}
