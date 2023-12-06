package com.evg.order.service.client;

import com.evg.order.dto.billing.request.BillingAccountUpdateRequest;
import com.evg.order.dto.billing.response.BillingAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "billing", url = "${billing.server}", path = "/billing")
public interface BillingClient {

    @PutMapping("/account/top-up/{userId}")
    BillingAccountResponse changeAccountAmount(@PathVariable("userId") Long userId,
                                               @RequestBody BillingAccountUpdateRequest request,
                                               @RequestHeader("x-auth-token") String authToken);

}
