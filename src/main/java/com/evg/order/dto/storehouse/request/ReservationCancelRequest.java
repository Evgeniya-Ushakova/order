package com.evg.order.dto.storehouse.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCancelRequest {

    private List<Long> reservationIds;

}
