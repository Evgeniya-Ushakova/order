package com.evg.order.dto.storehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorehouseProductDto {

    private Long productId;
    private Long count;
    private Long orderId;

}
