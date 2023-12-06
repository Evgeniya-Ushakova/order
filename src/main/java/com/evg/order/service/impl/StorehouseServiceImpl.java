package com.evg.order.service.impl;

import com.evg.order.dto.storehouse.StorehouseProductDto;
import com.evg.order.dto.storehouse.request.ReservationCancelRequest;
import com.evg.order.dto.storehouse.request.ReservationRequest;
import com.evg.order.dto.storehouse.response.ReservationResponse;
import com.evg.order.entity.Order;
import com.evg.order.repository.OrderRepository;
import com.evg.order.service.StorehouseService;
import com.evg.order.service.client.StorehouseClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j(topic = "STOREHOUSE_SERVICE")
@RequiredArgsConstructor
public class StorehouseServiceImpl implements StorehouseService {

    private final StorehouseClient storehouseClient;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    @Override
    public ReservationResponse reserveProduct(String orderKey) {
        Order order = orderRepository.findByOrderKeyOrElseThrow(orderKey);

        List<StorehouseProductDto> storehouseProductDtos = order.getProducts().stream()
                .map(product -> {
                    StorehouseProductDto storehouseProductDto = new StorehouseProductDto();
                    storehouseProductDto.setProductId(product.getId().getProductId());
                    storehouseProductDto.setOrderId(product.getId().getOrderId());
                    storehouseProductDto.setCount(product.getCount());
                    return storehouseProductDto;
                })
                .toList();

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setProducts(storehouseProductDtos);

        return storehouseClient.reserve(reservationRequest);

    }

    @Override
    public ReservationResponse cancelReservation(List<Long> reservationIds) {
        ReservationCancelRequest request = new ReservationCancelRequest();
        request.setReservationIds(reservationIds);

        return storehouseClient.disbandment(request);
    }

}
