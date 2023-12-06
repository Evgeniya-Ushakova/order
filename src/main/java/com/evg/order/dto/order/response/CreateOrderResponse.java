package com.evg.order.dto.order.response;

import com.evg.order.dto.BaseResponse;
import com.evg.order.dto.order.ProductDto;
import com.evg.order.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateOrderResponse extends BaseResponse {

    private String orderKey;
    private Long orderId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ProductDto> products;
    private BigDecimal totalPrice;
    private OrderStatus status;

}
