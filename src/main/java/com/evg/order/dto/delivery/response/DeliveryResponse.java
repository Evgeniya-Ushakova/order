package com.evg.order.dto.delivery.response;

import com.evg.order.dto.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryResponse extends BaseResponse {

    private Long reservedId;
    private Long courierId;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    private Long orderId;

}
