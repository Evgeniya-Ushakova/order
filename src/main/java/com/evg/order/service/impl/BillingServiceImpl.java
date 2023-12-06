package com.evg.order.service.impl;

import com.evg.order.dto.billing.request.BillingAccountUpdateRequest;
import com.evg.order.dto.billing.response.BillingAccountResponse;
import com.evg.order.service.BillingService;
import com.evg.order.service.client.BillingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.evg.order.service.impl.AuthServiceImpl.USER_TOKEN;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "BILLING_SERVICE")
public class BillingServiceImpl implements BillingService {

    private final BillingClient billingClient;

    @Override
    public BillingAccountResponse updateBillingAccount(Long userId, BigDecimal totalPrice, boolean increase) {
        BillingAccountUpdateRequest request = BillingAccountUpdateRequest.builder()
                .amountToIncrease(increase ? totalPrice : null)
                .amountToDecrease(!increase ? totalPrice : null)
                .build();
        return billingClient.changeAccountAmount(userId, request, USER_TOKEN);
    }

}
