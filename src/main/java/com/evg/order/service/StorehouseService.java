package com.evg.order.service;

import com.evg.order.dto.storehouse.response.ReservationResponse;

import java.util.List;

public interface StorehouseService {

    ReservationResponse reserveProduct(String orderKey);

    ReservationResponse cancelReservation(List<Long> reservationIds);

}
