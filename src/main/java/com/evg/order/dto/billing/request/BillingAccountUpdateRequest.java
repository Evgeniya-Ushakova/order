package com.evg.order.dto.billing.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingAccountUpdateRequest {

    private BigDecimal amountToIncrease;
    private BigDecimal amountToDecrease;

}
