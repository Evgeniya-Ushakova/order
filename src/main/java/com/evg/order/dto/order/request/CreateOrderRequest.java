package com.evg.order.dto.order.request;

import com.evg.order.dto.order.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @NotEmpty
    private List<ProductDto> products;
    @NotNull
    private BigDecimal totalPrice;
    @NotNull
    private Long userId;

}
