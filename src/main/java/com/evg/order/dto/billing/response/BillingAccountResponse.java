package com.evg.order.dto.billing.response;


import com.evg.order.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BillingAccountResponse extends BaseResponse {

    private Long userId;
    private Long accountId;
    private BigDecimal currentAmount;

}
