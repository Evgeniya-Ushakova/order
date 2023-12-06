package com.evg.order.dto.delivery.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequest {

    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    private Long orderId;
    private String address;

}