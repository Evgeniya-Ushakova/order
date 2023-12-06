package com.evg.order.dto.storehouse.message;

import com.evg.order.enums.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrelationMessageMessage {

    private String orderKey;
    private String status;
    private OrderEvent event;
    private Boolean success;
    private String errorMessage;

}
