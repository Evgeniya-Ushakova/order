package com.evg.order.dto.storehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCancelDto {

    private Long reservationId;
    private String status;

}
