package com.evg.order.dto.storehouse.request;

import com.evg.order.dto.storehouse.StorehouseProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private List<StorehouseProductDto> products;

}
