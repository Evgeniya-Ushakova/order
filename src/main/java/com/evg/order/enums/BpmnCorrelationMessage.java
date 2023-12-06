package com.evg.order.enums;

import lombok.Getter;

@Getter
public enum BpmnCorrelationMessage {

    ORDER_PACKAGED,
    ORDER_DELIVERY,
    ORDER_CANCELING,
    ORDER_BACKED,
    ORDER_DISBANDMENT

}
