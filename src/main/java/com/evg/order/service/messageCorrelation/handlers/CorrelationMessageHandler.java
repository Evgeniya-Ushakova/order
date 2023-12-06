package com.evg.order.service.messageCorrelation.handlers;

import com.evg.order.dto.storehouse.message.CorrelationMessageMessage;

public interface CorrelationMessageHandler {

    void correlateMessage(CorrelationMessageMessage message);

}
