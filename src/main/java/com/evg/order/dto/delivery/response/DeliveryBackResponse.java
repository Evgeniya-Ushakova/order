package com.evg.order.dto.delivery.response;

import com.evg.order.dto.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryBackResponse extends BaseResponse {

    private Long reservationId;
    private String status;

}
