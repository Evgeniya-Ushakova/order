package com.evg.order.service.client;

import com.evg.order.dto.storehouse.request.ReservationCancelRequest;
import com.evg.order.dto.storehouse.request.ReservationRequest;
import com.evg.order.dto.storehouse.response.ReservationCancelResponse;
import com.evg.order.dto.storehouse.response.ReservationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "storehouse", url = "${storehouse.server}", path = "/product")
public interface StorehouseClient {

    @PostMapping("/reserve")
    ReservationResponse reserve(ReservationRequest reservationRequest);

    @PostMapping("/disbandment")
    ReservationResponse disbandment(ReservationCancelRequest request);


}
