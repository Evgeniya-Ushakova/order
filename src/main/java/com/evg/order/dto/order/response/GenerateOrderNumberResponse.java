package com.evg.order.dto.order.response;

import com.evg.otus.dto.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GenerateOrderNumberResponse extends BaseResponse {

    private Long orderNumber;

}
