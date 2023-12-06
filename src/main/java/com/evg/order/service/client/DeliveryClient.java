package com.evg.order.service.client;

import com.evg.order.dto.delivery.request.DeliveryBackRequest;
import com.evg.order.dto.delivery.request.DeliveryRequest;
import com.evg.order.dto.delivery.response.DeliveryBackResponse;
import com.evg.order.dto.delivery.response.DeliveryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "delivery", url = "${delivery.server}", path = "/delivery")
public interface DeliveryClient {

    @PostMapping("/reserve-delivery")
    DeliveryResponse reserveDelivery(@RequestBody DeliveryRequest request);

    @PostMapping("/back")
    DeliveryBackResponse backToStorehouse(@RequestBody DeliveryBackRequest request);

}
