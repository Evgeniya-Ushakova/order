package com.evg.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BpmnProcess {

    CREATE_ORDER("order_01_create_order");

    String processId;

    BpmnProcess(String processId) {
        this.processId = processId;
    }

}
