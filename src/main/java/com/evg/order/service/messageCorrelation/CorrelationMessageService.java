package com.evg.order.service.messageCorrelation;

import com.evg.order.dto.storehouse.message.CorrelationMessageMessage;

public interface CorrelationMessageService {

    void correlateMessage(CorrelationMessageMessage message);

}
