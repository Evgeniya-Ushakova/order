package com.evg.order.service;

import com.evg.order.dto.billing.response.BillingAccountResponse;

import java.math.BigDecimal;

public interface BillingService {

    BillingAccountResponse updateBillingAccount(Long userId, BigDecimal totalPrice, boolean increase);

}
